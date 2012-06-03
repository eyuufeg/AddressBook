AddressBook application provides basic function such as add ,find ,list and quit to operator an addressbook.
First of all,make sure maven has been installed ,create package by executing commands:
#cd AddressBook
#mvn package  to produce a jar file.
Second,ilaunchapplication by executing commands:
#cd target
#java -jar xxx.jar

When the screen show that "Please input command:", you should input related command to manage this addresbook.
Command listed as following:
1.add a user. eg,'add James'
2.find a user info by his/her number. eg, 'find <number>'
3.list all the users. eg, 'list'
4.quit the application. eg, 'quit'
=============
Note:
1.when find a user's info, you can either input the number completed or partial
2.when input quit to end the application, it will be store the info of user which you have add in phonebook.xml
3.all the log file can be found at ./logs

