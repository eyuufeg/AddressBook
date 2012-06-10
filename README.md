<p>===========================================================
Preface </p>

<h1>AddressBook application provides basic function such as add ,find ,list and quit. Make sure JDK and maven has been installed.</h1>

<p>Function introduction
1.add user info
add new user include his/her name number and address info to your addressbook
2.find user by his/her number
   search suited user by inputting partial or complete number
3.quickly find user by his/her number
   search suited user by inputting number
4.list all user�s info
   display all existing users 
5.quit application</p>

<h1>when quit application, it will save users� info which you have added and quit</h1>

<p>Do as following steps to package source code and run program:
1.change directory to AddressBook
2.input �mvn package� then you can see target
3.change directory to target
4.input �java �jar AddressBook-1.0-SNAPSHOT.jar� to start program
When screen show as following</p>

<p><strong><em>*</em><em>*</em><em>*</em><em>*</em></strong>ADDRESS BOOK APPLICATION STARTING<strong><em>*</em><em>*</em><em>*</em><em>*</em></strong>
<strong><em>*</em><em>*</em><em>*</em><em>*</em></strong>USAGE OF THE COMMANDS            <strong><em>*</em><em>*</em><em>*</em><em>*</em></strong>
                add <name> <br />
                find <number> <br />
                quickfind <number> <br />
                list <br />
                quit <br />
<hr />
Please input your command: </p>

<p>1.add a user. Please input 'add James' 
  Enter number: �123�
  Enter address: �shanghai�
2.find a user info by his/her partial number. Please input 'find XXX' <br />
3.quickly find a user info by his/her full number. Please input 'quickfind XXX' 
4.list all the users. Please input 'list'</p>

<h1>5.quit the application. Please input 'quit'</h1>

<p>Note:
1.when find a user's info, you can either input the number completed or partial
2.when input quit to end the application, it will be store the info of user which you have add in phonebook.xml
3.all the log file can be found at ./logs</p>