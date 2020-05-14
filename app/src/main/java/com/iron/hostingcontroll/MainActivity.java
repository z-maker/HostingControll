package com.iron.hostingcontroll;

import android.content.Context;
import android.os.Bundle;
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
import com.iron.adapters.HostingAdapter;
import com.iron.data.entities.HostingEntity;
import com.iron.data.vm.HostingViewModel;
import com.iron.model.HostingEntry;
import com.iron.sql.hosting_entries.HostingEntriesAdapter;
import com.iron.sql.hosting_entries.HostingEntryHelper;
import com.iron.ui.Dialogs.DynamicDialog;
import com.iron.ui.Dialogs.DynamicDialogFormListener;

import java.util.List;

public class MainActivity extends AppCompatActivity  implements DynamicDialogFormListener {

    /**Room implementation*/
    private HostingViewModel hostingViewModel;


    private Context ctx;
    private DynamicDialog dialog;
    private HostingEntryHelper db;

    private RecyclerView rvHostingList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ctx = this;

        rvHostingList = findViewById(R.id.rvHostingList);
        rvHostingList.setLayoutManager(new LinearLayoutManager(this));
        rvHostingList.setHasFixedSize(true);

        final HostingAdapter adapter = new HostingAdapter();
        rvHostingList.setAdapter(adapter);

        /**Room implementation **/
        hostingViewModel = new ViewModelProvider(this).get(HostingViewModel.class);
        hostingViewModel.getAll().observe(this, new Observer<List<HostingEntity>>() {
            @Override
            public void onChanged(List<HostingEntity> hostingEntities) {
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



//         db = new HostingEntryHelper(this);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        rvHostingList = findViewById(R.id.rvHostingList);
//        rvHostingList.setLayoutManager(new LinearLayoutManager(this));
//        rvHostingList.setHasFixedSize(true);

//        rvHostingList.setAdapter(new HostingEntriesAdapter(db.getAllAsList()));

        dialog = new DynamicDialog.Builder(ctx).OnPositiveClicked(this).build();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnClick(HostingEntry post) {
        boolean result  = db.add(post);
        if (result){
            Toast.makeText(this,"Se agrego: "+post.getUsername()+ " a la lista",Toast.LENGTH_SHORT).show();
            rvHostingList.setAdapter(new HostingEntriesAdapter(db.getAllAsList()));

        }
        dialog.dismiss();
    }
}
