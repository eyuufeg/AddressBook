package com.ericsson.javatraining.addressbook;


public class CommandProcess {
    /**
     * getCommand should keep working
     */
    private ReadAndWrite addressbook = new ReadAndWrite();
    private AddrBookImp addrbookimpl = new AddrBookImp();

    public void process() {
        String cmd = addrbookimpl.getString("Please input your command: ");
        if (cmd.startsWith("add ")) {
            addrbookimpl.add(cmd.substring(cmd.indexOf(' ') + 1), addressbook);

        } else if (cmd.startsWith("find ")) {
            addrbookimpl.find(cmd.substring(cmd.indexOf(' ') + 1), addressbook);
        } else if (cmd.equals("list")) {
            addrbookimpl.list(addressbook);
        } else if (cmd.equals("quit")) {
            addrbookimpl.quit(addressbook);
        } else {
            System.out.println("unknown command!");
        }

    }



}
