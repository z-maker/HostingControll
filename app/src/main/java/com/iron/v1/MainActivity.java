package com.iron.v1;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.iron.hostingcontroll.R;
import com.iron.v1.adapters.HostingAdapter;
import com.iron.v1.data.entities.HostingEntity;
import com.iron.v1.data.vm.HostingViewModel;
import com.iron.v1.model.HostingEntry;

import com.iron.v1.ui.Dialogs.DynamicDialog;
import com.iron.v1.ui.Dialogs.DynamicDialogFormListener;
import com.iron.v1.utils.CsvParser;
import com.iron.v1.utils.DomainChecker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity  implements DynamicDialogFormListener {

    private static final String TAG = "KK";
    private static final int FILE_ACCESS = 0;

    private List<HostingEntity> currentEntities = new ArrayList<>();

    /**Room implementation*/
    private HostingViewModel hostingViewModel;

    private Context ctx;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ctx = this;

        RecyclerView rvHostingList = findViewById(R.id.rvHostingList);
        rvHostingList.setLayoutManager(new LinearLayoutManager(this));
        rvHostingList.setHasFixedSize(true);
        rvHostingList.setItemViewCacheSize(40);

        final HostingAdapter adapter = new HostingAdapter();
        rvHostingList.setAdapter(adapter);

        /**Room implementation **/
        hostingViewModel = new ViewModelProvider(this).get(HostingViewModel.class);
        hostingViewModel.getAll().observe(this, new Observer<List<HostingEntity>>() {
            @Override
            public void onChanged(List<HostingEntity> hostingEntities) {
                currentEntities =hostingEntities;
                adapter.setList(hostingEntities);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                hostingViewModel.delete(adapter.getObjectAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(rvHostingList);

        DynamicDialog dialog = new DynamicDialog.Builder(ctx).OnPositiveClicked(this).build();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

                String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
                EasyPermissions.requestPermissions(MainActivity.this,"Se necesita permiso para leer archivos",FILE_ACCESS,perms);


            }
        });
    }

    @AfterPermissionGranted(FILE_ACCESS)
    public void filePermissionsResults(){
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {

            Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
            chooseFile.addCategory(Intent.CATEGORY_OPENABLE);
            chooseFile.setType("text/*");
            chooseFile = Intent.createChooser(chooseFile, "Choose a file");
            startActivityForResult(chooseFile, 0);

        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(MainActivity.this,"Se necesita permiso para leer archivos",FILE_ACCESS,perms);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_delete_all:
                hostingViewModel.deleteAll();
                return true;
            case R.id.action_update_all:
                 updateData(currentEntities);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnClick(HostingEntry post) {

    }

    private void updateData(final List<HostingEntity> hostingEntities){

        progressDialog = new ProgressDialog(ctx);
        progressDialog.setTitle("Actualizanto datos");
        progressDialog.setMessage("0/"+hostingEntities.size());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(hostingEntities.size());
        progressDialog.setCancelable(false);
        progressDialog.show();

        DomainChecker checker = DomainChecker.getInstance().setOnProgressListener(new DomainChecker.OnDomainCheckProgress() {
            @Override
            public void onProgressUpdate(int progress) {
                progressDialog.setMessage(progress+"/"+hostingEntities.size());
                progressDialog.setProgress(progress);
            }
        }).setOnResultListener(new DomainChecker.OnDomainCheckResultFromList() {
            @Override
            public void onResult(List<HostingEntity> resultList) {
                hostingViewModel.update(resultList);
                progressDialog.dismiss();
                Toast.makeText(ctx,"Success",Toast.LENGTH_LONG).show();
            }
        });

        checker.Starts(hostingEntities);
    }

    private void loadFromCsv(File file) throws IOException {

        progressDialog = new ProgressDialog(ctx);
        progressDialog.setTitle("Importando datos");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();

        CsvParser parser = CsvParser.getInstance().withParams(true,',');
        parser.addProgressListener(new CsvParser.OnCsvParserProgress() {
            @Override
            public void onCsvParserProgress(String progress) {
                progressDialog.setMessage(progress);
            }
        });
        parser.addResultListener(new CsvParser.OnCsvParserResult() {
            @Override
            public void onCsvParserResult(List<HostingEntity> list) {
                hostingViewModel.insert(list);
                progressDialog.dismiss();
            }
        });
        parser.parse(file);

    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {

                MediaStore.Downloads.DISPLAY_NAME,
                MediaStore.DownloadColumns.MIME_TYPE,
                MediaStore.DownloadColumns.DOCUMENT_ID,
                MediaStore.DownloadColumns.SIZE
        };
        Cursor cursor = getApplicationContext()
                .getContentResolver()
                .query(contentUri,
                        proj, null, null, null);

        if (cursor.moveToFirst()) {
            int index = 0;
            while (index < cursor.getColumnCount()){

                String re = cursor.getColumnName(index);

                String val = cursor.getString(index);
                Log.d("KK",re+"--"+val);
                index++;

            }
            Uri rr = MediaStore.Files.getContentUri("raw",1182);
            Log.d("KK",rr.toString());
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.DownloadColumns.DOCUMENT_ID);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == -1) {
                    Uri fileUri = data.getData();
                    String filePath =  getPathFromURI(fileUri);//fileUri.getPath().split(":")[1];
                    File file = new File(filePath);
                    try {
                        loadFromCsv(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }

    }

}
