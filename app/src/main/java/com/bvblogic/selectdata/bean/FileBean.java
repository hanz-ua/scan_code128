package com.bvblogic.selectdata.bean;

import android.widget.Toast;

import com.bvblogic.selectdata.bean.core.BaseBean;
import com.opencsv.CSVWriter;

import org.androidannotations.annotations.EBean;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

@EBean
public class FileBean extends BaseBean {

    public void saveData(Collection<String[]> data,String name) {
        String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = name + "-AnalysisData.csv";
        String filePath = baseDir + File.separator + fileName;
        File f = new File(filePath);
        CSVWriter writer = null;
        if (f.exists() && !f.isDirectory()) {
//            FileWriter mFileWriter = null;
//            try {
//                mFileWriter = new FileWriter(filePath, true);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            writer = new CSVWriter(mFileWriter);
            f.delete();
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                writer = new CSVWriter(new FileWriter(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                writer = new CSVWriter(new FileWriter(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Objects.requireNonNull(writer).writeAll(data);

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toast.makeText(baseActivity, "all data save", Toast.LENGTH_SHORT).show();
    }
}
