#AddressBook#
AddressBook application provides basic function such as add ,find ,list and quit. Make sure JDK and maven has been installed.

##Basic Function##
1.add new user include his/her name number and address info to your addressbook

2.search suited user by inputting partial or complete number

3.search suited user by inputting number

4.display all existing users 

5.when quit application, it will save users info which you have added and quit

##Steps##
Follow the steps below to package the sourcecode and run the program:

1.change directory to AddressBook

2.input 'mvn package' then you can see target

3.change directory to target

4.input 'java -jar AddressBook-1.0-SNAPSHOT.jar' to start program

When screen show as following
*********************************************************

================USAGE OF THE COMMANDS============================  

                add <name>   
                                    
                find <number>  
                                  
                quickfind <number>
                               
                list              
                               
                quit           
                                  
=================================================================

Please input your command: 

*****************************************************************


1.add a user. please input 'add James' 

  Enter number: '123'

  Enter address: 'shanghai'

2.find a user info by his/her partial number. please input 'find XXX' 
 
3.quickly find a user info by his/her full number. please input 'quickfind XXX'

4.list all the users. please input 'list'

5.quit the application. please input 'quit'

##Note##
1.when find a user's info, you can either input the number completed or partial

2.when input quit to end the application, it will be store the info of user which you have add in phonebook.xml

3.all the log file can be found at ./logs

