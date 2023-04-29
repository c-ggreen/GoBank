# GoBank
This project mimics what a banking application would be. It's main goal is to serve as a personal project that I can add to over a long period of time to show mastery of skill/proof of concept.

## Planned Features or Changes
- 4/28/2023
    - ~Accounts shouldn't be permanently deleted. Instead they should have a status of OPEN, HOLD, or CLOSED. That way they can be deleted from a Customer's existing list, but we can retain the account info for future use.~

## Logs
- 4/28/2023
    - Added an Account Status enum to server code
    - Updated error handling for Account Service
    - Updated API Response for Account Controller
    - Added logic to Account Update method that removes accounts whose status is CLOSED from Customer accounts list
    - Started markdown

## Known Issues
 