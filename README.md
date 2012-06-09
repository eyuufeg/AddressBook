===========================================================
Preface 
AddressBook application provides basic function such as add ,find ,list and quit. Make sure JDK and maven has been installed.
===========================================================
Function introduction
add user info
add new user include his/her name number and address info to your addressbook
find user by his/her number
   search suited user by inputting partial or complete number
list all user’s info
   display all existing users 
quit application
when quit application, it will save users’ info which you have added and quit
===========================================================

Do as following steps to package source code and run program:
1.	change directory to AddressBook
2.	input ‘mvn package’ then you can see target
3.	change directory to target
4.	input ‘java –jar AddressBook-1.0-SNAPSHOT.jar’ to start program
When screen show as following
 
****************ADDRESS BOOK APPLICATION STARTING****************
****************USAGE OF THE COMMANDS            ****************
                add <name>
                find <number>
                list
                quit
*****************************************************************
Please input your command: 

1.add a user. Please input 'add James' 
  Enter number: ‘123’
  Enter address: ‘shanghai’
2.find a user info by his/her number. Please input 'find XXX'  
3.list all the users. Please input 'list'
4.quit the application. Please input 'quit'
=============
Note:
1.when find a user's info, you can either input the number completed or partial
2.when input quit to end the application, it will be store the info of user which you have add in phonebook.xml
3.all the log file can be found at ./logs


