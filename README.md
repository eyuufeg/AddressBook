Preface
AddressBook application provides basic function such as add ,find ,list and quit. Make sure JDK and maven has been installed.

Function introduction
Follow the steps below to package the sourcecode and run the program:
1.add user info
add new user include his/her name number and address info to your addressbook

2.find user by his/her number
   search suited user by inputting partial or complete number

3.quickly find user by his/her number
   search suited user by inputting number

4.list all user info
   display all existing users 

5.quit application
and you can see the console interface of the program

read addressBook....................
************************************************************************
                         AddressBook                                    
                  *A:add new User Address   
                  *D:delete User            
                  *S:search User by phoneNum
                  *L:list all User          
                  *Q:Quit the program       
************************************************************************
please input the option(A/D/S/L/Q):

1.If you input A option,you can add new User Address, and please follow the prompts to input
UserName,Address,PhoneNumber of the user, be careful when you input the phoneNumber,the phoneNumber
should be follow the pattern "^[0-9]{3}[-][0-9]{7,8}$",like 021-34322123.

2.If you input D option, you can delete User,and then follow the prompts to input the UserName 
which you want to delete.If not exist the username in the addressbook,It will prompt "not exist this user"

3.If you input S option, you can search user by phone number, either complete or partial ,and then follow the prompts 
to input the phoneNumber,It will list the users.

4.If you input L option, you can see all the users you have stored.

5.If you input Q option, you will exit the program.and you would see the prompt
"confirm exist(Y/N):",If you input y,the program exit,and the users you have added will be store in xml format.
and when you run the program next time,choose L option, you  can see the users.If you input N,the program will continue.

