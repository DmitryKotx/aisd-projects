package com.company;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String n = "";
        String a = "";
        String m = "";
        myClass x = new myClass();
         while (true) {
            choice1();
            int choice1 = scanner.nextInt();
            if (choice1 == 1) {
                    while (true) {
                        System.out.println("1. Имя");
                        System.out.println("2. Доступ");
                        System.out.println("3. Модификатор");
                        System.out.println("4. Продолжить");
                        System.out.print("choose number: ");
                        int forClass = scanner.nextInt();
                        if (forClass == 1) {
                            Scanner scannerClass = new Scanner(System.in);
                            System.out.print("Добавьте или измените имя класса: ");
                            String name = scannerClass.nextLine();
                            if (name.equals("")) {
                                System.out.println("Вы ввели пустую строку");
                            } else {
                                n = name;
                            }
                        }
                        String access = "";
                        if (forClass == 2) {
                            access = access();
                            if (!n.equals("")) {
                                a = access;
                            } else {
                                System.out.println("Добавьте имя класса");
                            }
                        }
                        String modF = "";
                        if (forClass == 3) {
                            modF = modF();
                            if (!n.equals("")) {
                                m = modF;
                            } else {
                                System.out.println("Добавьте имя класса");
                            }
                        }
                        if (forClass == 4) {
                            if (!n.equals("")) {
                                break;
                            } else {
                                System.out.println("Добавьте имя класса");
                            }
                        }
                    }

                while (true) {
                    choice2();
                    int choice2 = scanner.nextInt();
                    if (choice2 == 1) {
                        System.out.println("1. Создать поле");
                               if (x.sizeF() != 0) {
                            for (int i = 0; i < x.sizeF(); i++) {
                                if (!x.getF(i).getName().equals(""))
                                    System.out.println(i+2 + ". Редактировать поле " + x.getF(i).getName());
                            }
                            System.out.println((x.sizeF()+2) + ". Удалить поле ");
                        }
                        System.out.print("Choose number: ");
                        int num = scanner.nextInt();
                        if (num == 1) {
                            x.addF();
                            num = x.sizeF();
                        }else if (num == x.sizeF()+2) {
                            for (int i = 0; i < x.sizeF(); i++) {
                                System.out.println((i+1) + ". поле " + x.getF(i).getName());
                            }
                            System.out.print("Choose number: ");
                            num = scanner.nextInt();
                            x.removeF(num-1);
                        } else  {
                            num -= 1;
                        }
                        while (true) {
                            Scanner scannerField = new Scanner(System.in);
                            String line = "";
                            while (true) {
                                System.out.println("1. Add or change field name");
                                System.out.println("2. Add or change field type");
                                System.out.print("Choose number: ");
                                int choice3_1_1 = scanner.nextInt();
                                if (choice3_1_1 == 1) {
                                    System.out.println("Введите или измените имя поля: ");
                                    line = scannerField.nextLine();
                                   x.getF(num-1).setName("");
                                    x.getF(num-1).setName(x.getF(num-1).getName() + line);
                                }
                                if (choice3_1_1 == 2) {
                                    System.out.println("Введите или измените тип поля: ");
                                    line = scannerField.nextLine();
                                    x.getF(num-1).setType("");
                                    x.getF(num-1).setType(x.getF(num-1).getType() + line);
                                }
                                if (!x.getF(num-1).getType().equals("") && !x.getF(num-1).getName().equals("")) {
                                    break;
                                } else {
                                    System.out.println("Необходимо заполнить оба пункта");
                                }
                            }
                            choice3_1();
                            int choice3_1 = scanner.nextInt();
                            if (choice3_1 == 1) {
                                System.out.println("Выберете или измените модификатор доступа поля: ");
                                line = access();
                                x.getF(num-1).setAccess("");
                                x.getF(num-1).setAccess(x.getF(num-1).getAccess() + line);
                            }
                            if (choice3_1 == 2) {
                                System.out.println("Введите значение поля: ");
                                line = scannerField.nextLine();
                                x.getF(num-1).setValue("");
                                x.getF(num-1).setValue(x.getF(num-1).getValue() + line);
                            }
                            if (choice3_1 == 3) {
                                System.out.println("Выберете или измените модификатор final поля: ");
                                line = modF();
                                x.getF(num-1).setModF("");
                                x.getF(num-1).setModF(x.getF(num-1).getModF() + line);
                            }
                            if (choice3_1 == 4) {
                                System.out.println("Выберете или измените модификатор static поля: ");
                                line = modS();
                                x.getF(num-1).setModS("");
                                x.getF(num-1).setModS(x.getF(num-1).getModS() + line);
                            }
                            if (choice3_1 == 5) {
                                break;
                            }

                        }
                    }
                    if (choice2 == 2) {

                        System.out.println("1. Создать конструктор");
                        if (x.sizeC() != 0) {
                            for (int i = 0; i < x.sizeC(); i++) {
                                    System.out.println(i+2 + ". Редактировать конструктор " + (i+1));
                            }
                            System.out.println((x.sizeC()+2) + " Удалить конструктор");
                        }
                        System.out.print("Choose number: ");
                        int num = scanner.nextInt();
                        if (num == 1) {
                            x.addC();
                            num = x.sizeC();
                        }else if (num == x.sizeC()+2) {
                            for (int i = 0; i < x.sizeC(); i++) {
                                System.out.println((i+1) + ". конструктор " + i+1);
                            }
                            System.out.print("Choose number: ");
                            num = scanner.nextInt();
                            x.removeC(num-1);
                        } else {
                            num -= 1;
                        }
                        int temp = num;
                        while (true) {
                            choice3_2();
                            int choice3_2 = scanner.nextInt();
                            String line = "";
                            if (choice3_2 == 1) {
                                System.out.println("Выберете или измените модификатор доступа конструктора: ");
                                line = access();
                                x.getC(num-1).setAccess("");
                                x.getC(num-1).setAccess(x.getC(num-1).getAccess() + line);
                            }
                            if (choice3_2 == 2) {
                                System.out.println("1. Создать параметр");
                                if (x.sizePC(temp-1) != 0) {
                                    for (int i = 0; i < x.sizePC(temp-1); i++) {
                                        if (!(x.getPC(temp-1, i)).getName().equals(""))
                                            System.out.println(i+2 + ". Редактировать параметр " + x.getPC(temp-1, i).getName());
                                    }
                                    System.out.println((x.sizePC(temp-1) + 2) + ". Удалить параметр.");
                                }
                                System.out.print("Choose number: ");
                                num = scanner.nextInt();
                                if (num == 1) {
                                    x.addPC(temp-1);
                                    num = x.sizePC(temp-1);
                                } else if (num == x.sizePC(temp-1)+2) {
                                    for (int i = 0; i < x.sizePC(temp-1); i++) {
                                        System.out.println(i+". параметр " + x.getPC(temp-1, i).getName());
                                    }
                                    System.out.print("Choose number: ");
                                    num = scanner.nextInt();
                                    x.removePC(temp-1, num-1);
                                } else  {
                                    num -= 1;
                                }
                                while (true) {
                                    Scanner scannerParameter = new Scanner(System.in);
                                    while (true) {
                                        if (x.getPC(temp - 1, num - 1).getType().equals("") || x.getPC(temp - 1, num - 1).getName().equals("")) {
                                            System.out.println("1. Add or change constructor parameter name");
                                            System.out.println("2. Add or change constructor parameter type");
                                            System.out.print("Choose number: ");
                                            int choice3_2_1 = scanner.nextInt();
                                            if (choice3_2_1 == 1) {
                                                System.out.println("Введите или измените имя параметра: ");
                                                line = scannerParameter.nextLine();
                                                x.getPC(temp - 1, num - 1).setName("");
                                                x.getPC(temp - 1, num - 1).setName(x.getPC(temp - 1, num - 1).getName() + line);

                                            }
                                            if (choice3_2_1 == 2) {
                                                System.out.println("Введите или измените тип параметра: ");
                                                line = scannerParameter.nextLine();
                                                x.getPC(temp - 1, num - 1).setType("");
                                                x.getPC(temp - 1, num - 1).setType(x.getPC(temp - 1, num - 1).getType() + line);

                                            }
                                            if (!x.getPC(temp - 1, num - 1).getType().equals("") && !x.getPC(temp - 1, num - 1).getName().equals("")) {
                                                break;
                                            } else {
                                                System.out.println("Необходимо заполнить оба пункта");
                                            }
                                        }

                                    }
                                    System.out.println("1. Add or change constructor parameter mod final");
                                    System.out.println("2. Back");
                                    System.out.println("Choose number: ");
                                    int par = scanner.nextInt();

                                    if (par == 1) {
                                        System.out.println("Выберете или измените модификатор final параметра: ");
                                        line = modF();
                                        x.getPC(temp-1, num-1).setModF("");
                                        x.getPC(temp-1, num-1).setModF(x.getPC(temp-1, num-1).getModF() + line);
                                    }
                                    if (par == 2) {
                                        break;
                                    }
                                }
                            }
                            if (choice3_2 == 3) {
                                break;
                            }
                        }

                    }
                    if (choice2 == 3) {
                        System.out.println("1. Создать метод");
                        if (x.sizeM() != 0) {
                            for (int i = 0; i < x.sizeM(); i++) {
                                if (!x.getM(i).getName().equals(""))
                                    System.out.println(i+2 + ". Редактировать метод " + x.getM(i).getName());
                            }
                            System.out.println((x.sizeM() +2) + ". Удалить метод");
                        }
                        System.out.print("Choose number: ");
                        int num = scanner.nextInt();

                        if (num == 1) {
                            x.addM();
                            num = x.sizeM();
                        } else if (num == x.sizeM() + 2) {
                            for (int i = 0; i < x.sizeM(); i++) {
                                System.out.println((i+1) + ". метод " + x.getM(i).getName());
                            }
                            System.out.print("Choose number: ");
                            num = scanner.nextInt();
                            x.removeM(num-1);
                        } else {
                            num -= 1;
                        }
                        int temp = num;
                        while (true) {
                            Scanner scannerMethod = new Scanner(System.in);
                            String line = "";
                            while (x.getM(num-1).getName().equals("") || x.getM(num-1).getType().equals("")) {
                                    System.out.println("1. Add or change method name");
                                    System.out.println("2. Add or change method type");
                                    System.out.print("Choose number: ");
                                    int choice3_3_1 = scanner.nextInt();
                                    if (choice3_3_1 == 1) {

                                        System.out.println("Введите или измените имя метода: ");
                                        line = scannerMethod.nextLine();
                                        x.getM(num - 1).setName("");
                                        x.getM(num - 1).setName(x.getM(num - 1).getName() + line);
                                    }
                                    if (choice3_3_1 == 2) {
                                        System.out.println("Введите или измените тип метода: ");
                                        line = scannerMethod.nextLine();
                                        x.getM(num - 1).setType("");
                                        x.getM(num - 1).setType(x.getM(num - 1).getType() + line);
                                    }
                                    if (!x.getM(num - 1).getType().equals("") && !x.getM(num - 1).getName().equals("")) {
                                        break;
                                    } else {
                                        System.out.println("Необходимо заполнить оба пункта");
                                    }
                            }
                            choice3_3();
                            int choice3_3 = scanner.nextInt();
                            if (choice3_3 == 1) {
                                System.out.println("Выберете или измените модификатор доступа метода: ");
                                line = access();
                                x.getM(num-1).setAccess("");
                                x.getM(num-1).setAccess(x.getM(num-1).getAccess() + line);
                            }
                            if (choice3_3 == 2) {
                                System.out.println("Выберете или измените модификатор final метода: ");
                                line = modF();
                                x.getM(num-1).setModF("");
                                x.getM(num-1).setModF(x.getM(num-1).getModF() + line);
                            }
                            if (choice3_3 == 3) {
                                System.out.println("Выберете или измените модификатор static метода: ");
                                line = modS();
                                x.getM(num-1).setModS("");
                                x.getM(num-1).setModS(x.getM(num-1).getModS() + line);
                            }
                            if (choice3_3 == 5) {
                                break;
                            }

                            if (choice3_3 == 4) {
                                System.out.println("1. Создать параметр");
                                if (x.sizePM(temp-1) != 0) {
                                    for (int i = 0; i < x.sizePM(temp-1); i++) {
                                        if (!(x.getPM(temp-1, i)).getName().equals(""))
                                            System.out.println(i+2 + ". Редактировать параметр " + x.getPM(temp-1, i).getName());
                                    }
                                    System.out.println((x.sizePM(temp-1) + 2) + ". Удалить параметр.");
                                }
                                System.out.print("Choose number: ");
                                num = scanner.nextInt();
                                if (num == 1) {
                                    x.addPM(temp-1, new parameterClass("",""));
                                    num = x.sizePM(temp-1);
                                } else if (num == x.sizePM(temp-1)+2) {
                                    for (int i = 0; i < x.sizePM(temp-1); i++) {
                                        System.out.println(i+". параметр " + x.getPM(temp-1, i).getName());
                                    }
                                    System.out.print("Choose number: ");
                                    num = scanner.nextInt();
                                    x.removePM(temp-1, num-1);
                                } else  {
                                    num -= 1;
                                }
                                while (true) {
                                    Scanner scannerParameter = new Scanner(System.in);
                                    while (true) {
                                        if (x.getPM(temp - 1, num - 1).getType().equals("") || x.getPM(temp - 1, num - 1).getName().equals("")) {
                                            System.out.println("1. Add or change constructor parameter name");
                                            System.out.println("2. Add or change constructor parameter type");
                                            System.out.print("Choose number: ");
                                            int choice3_2_1 = scanner.nextInt();
                                            if (choice3_2_1 == 1) {
                                                System.out.println("Введите или измените имя параметра: ");
                                                line = scannerParameter.nextLine();
                                                x.getPM(temp - 1, num - 1).setName("");
                                                x.getPM(temp - 1, num - 1).setName(x.getPM(temp - 1, num - 1).getName() + line);

                                            }
                                            if (choice3_2_1 == 2) {
                                                System.out.println("Введите или измените тип параметра: ");
                                                line = scannerParameter.nextLine();
                                                x.getPM(temp - 1, num - 1).setType("");
                                                x.getPM(temp - 1, num - 1).setType(x.getPM(temp - 1, num - 1).getType() + line);

                                            }
                                            if (!x.getPM(temp - 1, num - 1).getType().equals("") && !x.getPM(temp - 1, num - 1).getName().equals("")) {
                                                break;
                                            } else {
                                                System.out.println("Необходимо заполнить оба пункта");
                                            }
                                        }

                                    }
                                    System.out.println("1. Add or change constructor parameter mod final");
                                    System.out.println("2. Back");
                                    System.out.println("Choose number: ");
                                    int par = scanner.nextInt();

                                    if (par == 1) {
                                        System.out.println("Выберете или измените модификатор final параметра: ");
                                        line = modF();
                                        x.getPM(temp-1, num-1).setModF("");
                                        x.getPM(temp-1, num-1).setModF(x.getPM(temp-1, num-1).getModF() + line);
                                    }
                                    if (par == 2) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (choice2 == 4) {
                        break;
                    }
                }
            }
            if (choice1 == 2) {
                if (n.equals("")) {
                    System.out.println("Вы еще не создали класс");
                } else {
                    System.out.println(m + a + "class " + n + " {");
                    for (int i = 0; i < x.sizeF(); i++) {
                            if (!x.getF(i).getValue().equals("")) {
                                System.out.println(x.getF(i).getModF() + x.getF(i).getModS() + x.getF(i).getAccess() + x.getF(i).getType() + " " + x.getF(i).getName() + " = " + x.getF(i).getValue() + ";");

                            } else {
                                System.out.println(x.getF(i).getModF() + x.getF(i).getModS() +  x.getF(i).getAccess() + x.getF(i).getType() + " " + x.getF(i).getName() + ";");
                            }
                    }
                    String cPMod;
                    String cPName;
                    String cPType;
                    StringBuilder cPParam;

                    for (int i = 0; i < x.sizeC(); i++) {
                        cPParam = new StringBuilder();
                            for (int j = 0; j < x.sizePC(i); j++) {
                                cPMod = x.getPC(i, j).getModF();
                                cPName = x.getPC(i, j).getName();
                                cPType = x.getPC(i, j).getType();
                                if (j == x.sizePC(i) - 1) {
                                    cPParam.append(cPMod).append(" ").append(cPType).append(" ").append(cPName);
                                    break;
                                } else {
                                    cPParam.append(cPMod).append(" ").append(cPType).append(" ").append(cPName).append(", ");
                                }
                                if (x.getPC(i, j).getName().equals("") && x.getPC(i, j).getType().equals("")) {
                                    cPParam = new StringBuilder();
                                }
                        }
                        System.out.println(x.getC(i).getAccess() + n + " (" + cPParam + ") {\n}");
                    }
                    String mPMod;
                    String mPName;
                    String mPType = "";
                    StringBuilder mPParam;
                    String value;
                    for (int i = 0; i < x.sizeM(); i++) {
                            mPParam = new StringBuilder();
                                for (int j = 0; j < x.sizePM(i); j++) {
                                    mPMod = x.getPM(i, j).getModF();
                                    mPName = x.getPM(i, j).getName();
                                    mPType = x.getPM(i, j).getType();
                                    if (j == x.sizePM(i) - 1) {
                                        mPParam.append(mPMod).append(" ").append(mPType).append(" ").append(mPName);
                                        break;
                                    } else {
                                        mPParam.append(mPMod).append(" ").append(mPType).append(" ").append(mPName).append(", ");
                                    }
                                    if (x.getPM(i, j).getName().equals("") && x.getPM(i, j).getType().equals("")) {
                                        mPParam = new StringBuilder();
                                    }
                                }

                            if (mPType.equals("int") || mPType.equals("double")) {
                                value = "0";
                            } else if (mPType.equals("String")) {
                                value = "null";
                            } else if (mPType.equals("boolean")) {
                                value = "false";
                            } else {
                                value = "null";
                            }
                            System.out.println(x.getM(i).getModF() + x.getM(i).getModS() + x.getM(i).getAccess() + x.getM(i).getType() + " " + x.getM(i).getName() + " (" + mPParam + ") {\nreturn " + value + ";\n}");
                    }
                    System.out.println("}");
                }
            }
            if (choice1 == 3) {
                break;
            }
        }
    }
    public static void choice1() {
        System.out.println("1. Create class");
        System.out.println("2. Result");
        System.out.println("3. Finish");
        System.out.print("Choose number: ");
    }
    public static void choice2() {
        System.out.println("1. field");
        System.out.println("2. constructor");
        System.out.println("3. method");
        System.out.println("4. Back");
        System.out.print("Choose number: ");
    }
    public static void choice3_1() {
        System.out.println("1. Add or change field access");
        System.out.println("2. Add or change field value");
        System.out.println("3. Add or change field mod final");
        System.out.println("4. Add or change field mod static");
        System.out.println("5. Back");
        System.out.print("Choose number: ");
    }
    public static void choice3_2() {
        System.out.println("1. Add or change constructor access");
        System.out.println("2. Add, change or remove constructor parameter");
        System.out.println("3. Back");
        System.out.print("Choose number: ");
    }
    public static void choice3_3 () {
        System.out.println("1. Add or change method access");
        System.out.println("2. Add or change method mod final");
        System.out.println("3. Add or change method mod static");
        System.out.println("4. Add, change or remove method parameter");
        System.out.println("5. Back");
        System.out.print("Choose number: ");
    }
    public static String access () {
        System.out.println("1. privat");
        System.out.println("2. public");
        System.out.println("3. protected");
        System.out.println("4. по умолчанию");
        System.out.println("5. Back");
        System.out.print("Выберете номер: ");
        String per = "";
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n == 1) {
            per = "privat ";
        }
        if (n == 2) {
            per = "public ";
        }
        if (n == 3) {
            per = "protected ";
        }
        if (n == 4) {
            per = "";
        }
        return per;
    }
    public static String modF () {
        System.out.println("1. final");
        System.out.println("2. по умолчанию");
        System.out.println("3. Back");
        Scanner scanner = new Scanner(System.in);
        String per = "";
        int n = scanner.nextInt();
        if (n == 1) {
            per = "final ";
        }
        if (n == 2) {
            per = "";
        }
        return per;
    }
    public static String modS () {
        System.out.println("1. static");
        System.out.println("2. по умолчанию");
        System.out.println("3. Back");
        Scanner scanner = new Scanner(System.in);
        String per = "";
        int n = scanner.nextInt();
        if (n == 1) {
            per = "static ";
        }
        if (n == 2) {
            per = "";
        }
        return per;
    }

}