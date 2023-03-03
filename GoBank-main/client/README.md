# Notes
## To-Do
- [x] Create and Render Account Components
- [x] Need to add the ability for a user to edit an account (withdraw, deposit, etc), from a dedicated account page
    - if from dedicated page, I need to put the Account ID in state via Context
    - if from Dialog Box, I can get Account ID from the Homepage component and pass it to child component
- [x] Ability for User to Transfer
    - Will need a separate dialog box with transfer options (able to set which account you're transferring to)
- [] Ability for User to edit personal info
- [x] Proper Log Out button in Homepage Component
- [] Conditional rendering, a user has to be present to view certain components, if not present must be sent back to Landing Page Component
- [] When opening an Account for the first time, an initial transaction with the initial amount should be present
- [] Make it so that I don't have to push the entire object when patching an entity
    - As it stands the architecture is way too simple on my DB, resulting in unnecessarily complex front end logic
- [] Transactions need to be put in "Most Recent" order
## Problems
- When Account Page is reloaded via refresh button in browser, Axios returns an Array of Objects instead of an Object, leading to parsing errors in the DOM (Can't retrieve user because code is set to access an object, not an array of objects).
    - The problem is caused due to the User email not persisting on the front-end through a page reload, so it seems Axios defaults to the "Get All" method, resulting in an array of objects instead of the single object

- Had to switch to OtM Unidirectional instead of OtM Bidirectional due to problems I was having mapping my data to the database.

- Had to figure out how to Add Account to user object properly.
    - So when someone presses the "Add Account" button, it's safe to say an api call to get the credentials of the user is warranted. I also need these credentials to do a proper patch call later on. After the initial api call, I store the user info in local state, and push an object into the Accounts array for the User object in local state, then I push that altered User object into the patch API call, resulting in an updated database, and a new account object for the user.

