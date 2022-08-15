package com.example.test_login0.ui.login;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.test_login0.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class test_t extends AppCompatActivity
{
    private LineChart chart;
    ArrayList<sampleData> issueDataList;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);
        fac_Activity fac_activity= new fac_Activity();

        this.InitializeissueData();
        ListView listView = (ListView) findViewById(R.id.listView);
        final MyAdapter myAdapter = new MyAdapter(this, issueDataList);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id)
            {
//                Toast.makeText(getApplicationContext(),
//                        myAdapter.getItem(position).getFac_line(),
//                        Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext() , fac_Activity.class);
                intent.putExtra("fac_line", myAdapter.getItem(position).getFac_line()); //데이터 송신

                startActivity(intent);
            }
        });

        //차트코드
        chart = (LineChart) findViewById(R.id.data_flow);
        chart.setDrawGridBackground(true);
        chart.setBackgroundColor(Color.BLACK);
        chart.setGridBackgroundColor(Color.BLACK);

// description text
        chart.getDescription().setEnabled(true);
        Description des = chart.getDescription();
        des.setEnabled(true);
        des.setText("Factory DATA");
        des.setTextSize(15f);
        des.setTextColor(Color.WHITE);

// touch gestures (false-비활성화)
        chart.setTouchEnabled(false);

// scaling and dragging (false-비활성화)
        chart.setDragEnabled(false);
        chart.setScaleEnabled(false);

//auto scale
        chart.setAutoScaleMinMaxEnabled(true);

// if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false);

//X축
        chart.getXAxis().setDrawGridLines(true);
        chart.getXAxis().setDrawAxisLine(false);
        chart.getXAxis().setEnabled(true);
        chart.getXAxis().setDrawGridLines(false);

//Legend
        Legend l = chart.getLegend();
        l.setEnabled(true);
        l.setFormSize(10f); // set the size of the legend forms/shapes
        l.setTextSize(12f);
        l.setTextColor(Color.WHITE);

//Y축
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setEnabled(true);
        leftAxis.setTextColor(getResources().getColor(R.color.purple_200));
        leftAxis.setDrawGridLines(true);
        leftAxis.setGridColor(getResources().getColor(R.color.purple_700));
        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);

// don't forget to refresh the drawing
        chart.invalidate();
        float datanum = (float) 2.0;
       for(int i =0;i<100; i++)
       {
           addEntry(datanum);
       }

    }

    private void addEntry(double num) {

        LineData data = chart.getData();

        if (data == null) {
            data = new LineData();
            chart.setData(data);
        }

        ILineDataSet set = data.getDataSetByIndex(0);
        // set.addEntry(...); // can be called as well

        if (set == null) {
            set = createSet();
            data.addDataSet(set);
        }

        data.addEntry(new Entry((float)set.getEntryCount(), (float)num), 0);
        data.notifyDataChanged();

        // let the chart know it's data has changed
        chart.notifyDataSetChanged();

        chart.setVisibleXRangeMaximum(150);
        // this automatically refreshes the chart (calls invalidate())
        chart.moveViewTo(data.getEntryCount(), 50f, YAxis.AxisDependency.LEFT);

    }


    private LineDataSet createSet() {
        LineDataSet set = new LineDataSet(null, "Real-time Line Data");
        set.setLineWidth(1f);
        set.setDrawValues(false);
        set.setValueTextColor(getResources().getColor(R.color.white));
        set.setColor(getResources().getColor(R.color.white));
        set.setMode(LineDataSet.Mode.LINEAR);
        set.setDrawCircles(false);
        set.setHighLightColor(Color.rgb(190, 190, 190));

        return set;
    }



    public void make_Item(int image, String issue ,String fac_line, String moter_name, String day_time ,String moter_state,String solution)
    {
        issueDataList.add(new sampleData(image, issue, fac_line, moter_name, day_time, moter_state, solution));
    }

    public void InitializeissueData()
    {
        issueDataList = new ArrayList<sampleData>();

        //서버로 부터 화면에 띄워줄 아이템데이터를 받는다. 이슈,라인,모터명, 시기, 고장원인, 해결방안
        int s_issue = 0;
        int s_moter_state=0;

        //받은데이터의 int값을 배열에집어넣어 적합한 str으로 전환- 바로 집어넣어도대고

        //전환한str을 add에 전달
        String[]issues = {"정상", "고장","점검","교체","위험"};
        String[]fac_lines={"A1","A2","A3","A4","A5"};
        String[]moter_states={"정상","베어링불량","회전체불평형", "축정렬불량","벨트느슨함"};
        String[]solution={"부품교체","점검필요"};


        //add를 통해 아이템을 추가하고 해당하는 아이템을 띄워줌
        make_Item(R.drawable.ic_launcher_background,issues[s_issue], "A2","JHFF1124모터","2022-08-09", moter_states[s_moter_state],"모터교체필요");
        make_Item(R.drawable.ic_launcher_background,issues[s_issue], "A3","JHFF1122모터","2022-08-09", moter_states[s_moter_state],"모터교체필요");
        make_Item(R.drawable.ic_launcher_background,issues[s_issue], "A4","JHFF1121모터","2022-08-09", moter_states[s_moter_state],"모터교체필요");

    }

}
