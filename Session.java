package com.example.postureperfector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.chaquo.python.PyException;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Session extends AppCompatActivity {

    Button btnSave;

    String LOG_TAG = "Log Tag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        btnSave=findViewById(R.id.btnSave);
        TextView cpu = (TextView) findViewById(R.id.myTextView);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //writeFile();
                cpu.setText(readFile());
                //cpu.setText(PythonInterpreter());


               // writeFileOnInternalStorage(getApplicationContext(), Try_File.txt, PythonInterpreter());
            }
        });
            //public void onClick(View view) {
               // PythonInterpreter();
           // }
        //})

    }

    public void writeFile() {
       String FilePath = Environment.getExternalStorageDirectory().getAbsolutePath();
       String FileName = "DataFile.txt";
       File datafolder = new File(FilePath + "/Download/");
       datafolder.mkdirs();
       File data = new File(datafolder,FileName );
       Uri getSavedData = FileProvider.getUriForFile(
               getBaseContext(),BuildConfig.APPLICATION_ID + "." + getLocalClassName() + ".provider", data);
    }

    public static void main(String[] args) {

        // Creates an array of character
        char[] array = new char[100];

        try {
            // Creates a reader using the FileReader
            FileReader input = new FileReader("input.txt");

            // Reads characters
            input.read(array);
            System.out.println("Data in the file: ");
            System.out.println(array);

            // Closes the reader
            input.close();
        }

        catch(Exception e) {
            e.getStackTrace();
        }
    }



    public CharSequence readFile() {

        String FilePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File data = new File(FilePath + "/Download/");
        Log.i(LOG_TAG, data.toString());
        String fileName = "New.txt";
        StringBuilder stringBuilder = new StringBuilder();
        try(FileReader fileReader = new FileReader(data + fileName)) {



            char[] a = new char[2048];
            BufferedReader br = new BufferedReader(fileReader);
            //fileReader.read(a);

            String line = br.readLine();
            // Use a while loop to read the entire file
            while(line != null){
                // Append the line read to StringBuilder object. Also, append a new-line
                stringBuilder.append(line).append('\n');
                // Again read the next line and store in variable line
                line = br.readLine();
            }

            Log.i(LOG_TAG, "File can read: " + new File(data + fileName).canRead());

            Log.i(LOG_TAG, "File exists: " + data.exists());
            Log.i(LOG_TAG, "is File readable" + data.canRead());


            //verify content
            //System.out.println(new String(a));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            // Convert the StringBuilder content into String and add text "File contents\n"
            // at the beginning.
            String fileContents = "File contents\n" + stringBuilder.toString();

            System.out.println(fileContents);
        }


        //File f = new File(datafolder, fileName);

//        Log.i(LOG_TAG, "File exists: " + f.exists());
//        Log.i(LOG_TAG, "is File readable" + f.canRead());
//        try {
//            FileReader reader = new FileReader(f);
//            int ch;
//            while((ch= reader.read())!=-1)
//            {
//                builder.append((char));
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }


//        try {
//            FileInputStream fis = new FileInputStream(f);
//            byte b[] = new byte[fis.available()];
//            fis.read(b);
//            fis.close();
//        }

//
// Not sure if the / is on the path or not
//        try {
//        File f = new File(datafolder + File.separator + "/", fileName );
//        Log.i(LOG_TAG, f.toString());
//        Log.i(LOG_TAG, "File exists: " + f.exists());
//
//
//
////        f.mkdirs();
////            Log.i(LOG_TAG, "File exists: " + f.mkdirs());
//            boolean b = true;
//            f.setReadable(b);
//            f.read();
//            // Log.i(LOG_TAG, "File can read: " + f.setReadable(true, false));
//            Log.i(LOG_TAG, "File can read: " + f.canRead());
//
//            //FileInputStream fiStream = new FileInputStream(f);
//        } catch (Exception e){
//            e.printStackTrace();
//        }


        return null;
    }


    public String PythonInterpreter() {
        // 1. Start the Python instance if it isn't already running.
        if(!Python.isStarted()){
            Python.start(new AndroidPlatform(getApplicationContext()));
        }

        // 2. Obtain the python instance
        Python py = Python.getInstance();

        // 3. Declare some Python code that will be interpreted

// In our case, the fibonacci sequence
        //String code = "def fibonacci_of(n):\n";
        //code += "   if n in {0, 1}:  # Base case\n";
        //code += "       return n\n";
        //code += "   return n\n";
        //code += "\n";
        //code += "print([fibonacci_of(n) for n in range(10)])\n";

        String code = "import os\n";
        code += "import math\n";
        code += "d_path = '/storage/emulated/0/Download/'\n";
        code += "file_path = os.path.join(d_path, 'sensor_readings_movement404.csv')\n";
        code += "with open(file_path, 'r+') as file:\n";
        code += "   print(os.path.isfile(file_name))\n";
        code += "   Ax = []\n";
        code += "   Ay = []\n";
        code += "   Az = []\n";
        code += "   Ax_Numbers = []\n";
        code += "   Ay_Numbers = []\n";
        code += "   Az_Numbers = []\n";
        code += "   for line in file:\n";
        code += "       line_a = line.split(',')  # This puts the different columns as their elements in the list\n";
        code += "       Ax.append(line_a[0])\n";
        code += "       Ay.append(line_a[1])\n";
        code += "       Az.append(line_a[2])\n";
        code += "   for i in Ax:\n";
        code += "       a = float(i)\n";
        code += "       Ax_Numbers.append(a)\n";
        code += "   for i in Ay:\n";
        code += "       b = float(i)\n";
        code += "       Ay_Numbers.append(b)\n";
        code += "   for i in Az:\n";
        code += "       c = float(i)\n";
        code += "       Az_Numbers.append(c)\n";
        code += "   theta = float(0)\n";
        code += "   counter = int(0)\n";
        code += "   front_angle = int(0)\n";
        code += "   front_error = int(0)\n";
        code += "   backwards_angle = int(0)\n";
        code += "   backwards_error = int(0)\n";
        code += "   piece1 = float(0)\n";
        code += "   piece2 = float(0)\n";
        code += "   piece3 = float(0)\n";
        code += "   bottom = float(0)\n";
        code += "   top = float(0)\n";
        code += "   angle = float(0)\n";
        code += "   for i in range(len(Ax)):\n";
        code += "       if Ay_Numbers[i] and Ay_Numbers[i] and Az_Numbers[i] != 0:\n";
        code += "           piece1 = Ax_Numbers[i]**2\n";
        code += "           piece2 = Ay_Numbers[i] ** 2\n";
        code += "           piece3 = piece1 + piece2\n";
        code += "           bottom = math.sqrt(piece3)\n";
        code += "           top = Az_Numbers[i] / bottom\n";
        code += "           theta = math.atan(top)\n";
        code += "           angle = math.degrees(theta)\n";
        code += "           if angle > 20:\n";
        code += "               front_angle += 1\n";
        code += "               if front_angle == 5:\n";
        code += "                   front_error += 1\n";
        code += "                   front_angle = 0\n";
        code += "               if angle < -20:\n";
        code += "                   backwards_angle += 1\n";
        code += "                   if backwards_angle == 5:\n";
        code += "                       backwards_error += 1\n";
        code += "                       backwards_angle = 0\n";
        code += "               counter += 10\n";
        code += "               \n";
        code += "\n";

        // 4. Obtain the system's input stream (available from Chaquopy)
        PyObject sys = py.getModule("sys");
        PyObject io = py.getModule("io");
        // Obtain the interpreter.py module
        PyObject console = py.getModule("interpreter");

        // 5. Redirect the system's output stream to the Python interpreter
        PyObject textOutputStream = io.callAttr("StringIO");
        sys.put("stdout", textOutputStream);


        // 6. Create a string variable that will contain the standard output of the Python interpreter
        String interpreterOutput = "";

// 7. Execute the Python code
        try {
            console.callAttrThrows("mainTextCode", code);

            interpreterOutput = textOutputStream.callAttr("getvalue").toString();
        }catch (PyException e){
            // If there's an error, you can obtain its output as well
            // e.g. if you mispell the code
            // Missing parentheses in call to 'print'
            // Did you mean print("text")?
            // <string>, line 1
            interpreterOutput = e.getMessage().toString();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

// Outputs in the case of the fibonacci script:
// "[0, 1, 1, 2, 3, 5, 8, 13, 21, 34]"
        System.out.println(interpreterOutput);
        return interpreterOutput;
    }
    //Gonna put the timer here


}