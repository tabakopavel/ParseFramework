package molodost.bz.main;

import molodost.bz.db.ExcelRead;
import molodost.bz.db.MongoDB;
import molodost.bz.object.User;

import java.util.Map;

public class MainAddPeople {
    public static void main(String[] args) {
        MongoDB mongoDB = MongoDB.getConnection();
        ExcelRead excelRead = new ExcelRead();

        Map<String, String> tickets = excelRead.read("C:\\IntellijIdea\\UltimateEdition\\MolodostParse\\accounts.xls", "List");
        System.out.println(tickets.size());
        System.out.println(mongoDB);
        for (String s : tickets.keySet()) {
            System.out.println(s + "   " + tickets.get(s));

            mongoDB.addUserBot(new User(s, tickets.get(s)), "molodost","userBots");
        }
    }
}
