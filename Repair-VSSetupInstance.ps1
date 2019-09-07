#Requires -Version 3

# The MIT License (MIT) 
# Copyright (C) Microsoft Corporation. All rights reserved.
#
# Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

[CmdletBinding(SupportsShouldProcess = $true)]
param (
    [Parameter()]
    [switch] $Force
)

$ErrorActionPreference = 'Stop'

$loc = data {
    ConvertFrom-StringData @'
        Message_Confirm_Save = Save modified instance
        Error_Elevation_Required = You must run this script in an elevated command prompt
        Error_No_Product_Directory_Args2 = Instance {0} is corrupt: product directory not found: {1}
'@
}

$identity = [System.Security.Principal.WindowsIdentity]::GetCurrent()
$principal = New-Object System.Security.Principal.WindowsPrincipal $identity
if (!$principal.IsInRole([System.Security.Principal.WindowsBuiltInRole]::Administrator)) {
    throw $loc.Error_Elevation_Required
}

Write-Verbose 'Getting CachePath'
$cachePath = foreach ($key in @('Registry::HKEY_LOCAL_MACHINE\SOFTWARE\Policies\Microsoft\VisualStudio\Setup',
    'Registry::HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\VisualStudio\Setup',
    'Registry::HKEY_LOCAL_MACHINE\SOFTWARE\WOW6432Node\Microsoft\VisualStudio\Setup')) {
        Write-Verbose "Checking $key"
        $value = if (Test-Path $key) {
            $key = Get-ItemProperty $key
            $key.CachePath
        }

        if ($value) {
            $value
            Write-Verbose "Using CachePath: $value"
            break
        }
    }

if (!$cachePath) {
    $cachePath = "${env:ProgramData}\Microsoft\VisualStudio\Packages"
    Write-Verbose "Using default CachePath: $cachePath"
}


function format($id, $overrideId) {
    $name = if ($overrideId) { $overrideId } else { $id.id }
    if ($id.version) {
        $name += ',version=' + $id.version
    }
    if ($id.chip) {
        $name += ',chip=' + $id.chip
    }
    if ($id.language) {
        $name += ',language=' + $id.language
    }
    if ($id.branch) {
        $name += ',branch=' + $id.branch
    }

    $name
}

Write-Verbose 'Enumerating instances'
Join-Path $cachePath '_Instances' | Get-ChildItem -Recurse -Filter 'state.json' | ForEach-Object {
    $instanceDir = $_ | Split-Path | Get-Item
    $instanceId = $instanceDir.Name
    $instance = $_ | Get-Content -Raw -Encoding 'UTF8' | ConvertFrom-Json

    $title = '{0} ({1})' -f $instanceId, $instance.installationName
    Write-Verbose "Checking instance $title"

    $catalogFile = Join-Path $instanceDir 'catalog.json' -Resolve
    Write-Verbose "Indexing catalog for instance $instanceId"
    
    $catalog = Get-Content $catalogFile -Raw -Encoding 'UTF8' | ConvertFrom-Json
    $packages = $catalog.packages | Group-Object -Property @{e={$_.id}} -AsString -AsHashTable -NoElement
    Write-Verbose "Indexed $($packages.Count) package IDs"

    $product = $instance.product
    if ($product -isnot [string]) {
        $product = format $product
    }

    Write-Verbose "Checking product $product"

    $productDir = Join-Path $cachePath $product
    if (Test-Path $productDir) {
        $productFile = Join-Path $productDir '_package.json'
        $package = Get-Content $productFile -Raw -Encoding 'UTF8' | ConvertFrom-Json

        $modified = $false
        $package.dependencies | Get-Member -MemberType 'NoteProperty' | ForEach-Object {
            $dependencyId = $_.Name
            $dependency = $package.dependencies.$dependencyId
            $version = $null

            if (!$packages.ContainsKey($dependencyId)) {
                Write-Verbose "Removing dependency not found in catalog: $dependencyId"
                $package.dependencies.PSObject.Properties.Remove($dependencyId)

                $script:modified = $true

            } elseif ($dependencyId -like 'Component.*' -and [Version]::TryParse($dependency.version, [ref] $version)) {
                $partialId = format $dependency $dependencyId
                Write-Verbose "Checking for extension manifest: $partialId"

                $componentPath = Join-Path $cachePath $partialId
                if (Test-Path $componentPath) {
                    Get-ChildItem $componentPath -Filter '_package.json' | ForEach-Object {
                        $component = $_ | Get-Content -Raw -Encoding 'UTF8' | ConvertFrom-Json
                        if ($component.extension) {
                            $componentId = format $component

                            Write-Verbose "Removing extension dependency: $componentId"
                            $package.dependencies.PSObject.Properties.Remove($dependencyId)

                            $script:modified = $true
                        }
                    }
                } else {
                    Write-Verbose "Removing missing extension dependency: $dependencyId"
                    $package.dependencies.PSObject.Properties.Remove($dependencyId)

                    $script:modified = $true
                }
            }
        }

        if ($modified) {
            if ($Force -or $PSCmdlet.ShouldProcess($title, $loc.Message_Confirm_Save)) {
                $package | ConvertTo-Json -Compress -Depth 32 | Set-Content $productFile -Encoding 'UTF8' -Confirm:$false
            }
        }
    } else {
        Write-Warning ($loc.Error_No_Product_Directory_Args2 -f $instanceId, $productDir)
    }
}

<#
.SYNOPSIS
Performs some repairs on Visual Studio instances.

.DESCRIPTION
Repairs Visual Studio instances by removing missing extensions from the product manifest.

.PARAMETER Force
Save repaired instances without prompting.

.EXAMPLE
Repair-VSSetupInstance.ps1 -WhatIf
Automatically repair and save instances without prompting.

.EXAMPLE
Repair-VSSetupInstance.ps1 -WhatIf
See which instances would be repaired without actually saving or prompting.
#>
