
/* 
Прайсы
CrUD для таблицы внутри файла.
Считать с консоли имя файла для операций CrUD.

Программа запускается со следующим набором параметров:
-c productName price quantity

Значения параметров:
где id - 8 символов.
productName - название товара, 30 chars (60 bytes).
price - цена, 8 символов.
quantity - количество, 4 символа.
-c - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле.

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity

Данные дополнены пробелами до их длины.

Пример:
19846   Шорты пляжные синие           159.00 12
198478  Шорты пляжные черные с рисунко173.00 17
19847983Куртка для сноубордистов, разм10173.991234


Требования:
1. Программа должна считать имя файла для операций CrUD с консоли.
2. При запуске программы без параметров список товаров должен остаться неизменным.
3. При запуске программы с параметрами "-c productName price quantity" в конец файла должна добавится новая строка с товаром.
4. Товар должен иметь следующий id, после максимального, найденного в файле.
5. Форматирование новой строки товара должно четко совпадать с указанным в задании.
6. Созданные для файлов потоки должны быть закрыты.
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        List<String> buffer = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        String line = null;
        if (args.length  > 0)
            if (args[0].equals("-c")) {

                // read all string of file in buffer
                BufferedReader fin = new BufferedReader(new FileReader(fileName));
                while (fin.ready())
                    buffer.add(fin.readLine());
                fin.close();

                // define the next id
                int max = 0;
                for (String str : buffer) {
                    int id = Integer.parseInt(str.substring(0, 8).trim());
                    if (max < id)
                        max = id;
                }
                String id = Integer.toString(max + 1);

                // write all string to the file
                BufferedWriter fout = new BufferedWriter(new FileWriter(fileName));
                for (String str : buffer)
                    fout.write(str + "\n");

                // write the last string
                fout.write(String.format("%-8s%-30s%-8s%-4s",
                        id, args[1], args[2], args[3]));

                fout.close();
            }
    }
}