# User stories

### As a user, I want to see all my Abonnementen and all Abonnementen that are shared with me.

When I log in, I want to view a list of the following:

* All my Abonnementen.
* All Abonnementen that are shared with me.

### As a user, I want to see select an Abonnement from my list and view detail information.

The following buttons shoeld be shown:

* **Share** : Enable if this service has status other than 'opgezegd' AND user is owner (?????? right?).
* **Terminate** : Enable if this service has status other than 'opgezegd'.
* **Upgrade** : Enable if this service has status other than 'opgezegd' AND ????

What other information should be shown??? I guess it should be less than is present in the DomainModel

### As a user, I want to be able to share an Abonnement with another user.

The **Share** button should be enabled.

When the user clicks the button, a popup should be shown that displays a list of all users that are available for sharing this Abonnement with.

Note that this list should only contain users that do not have this Abonnement shared with yet.

### As a user, I want to terminate my Abonnement.

The **Terminate** button should be enabled.

When the user clicks the **Terminate** button, a confirmation Dialog should be appear. If the user confirms the action, the Abonnement should be terminated and change its state to 'opgezegd'.

