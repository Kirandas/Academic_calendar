package com.jcub.academic_calendar.month_view;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.imanoweb.calendarview.CalendarListener;
import com.imanoweb.calendarview.CustomCalendarView;
import com.imanoweb.calendarview.DayDecorator;
import com.imanoweb.calendarview.DayView;
import com.jcub.academic_calendar.R;
import com.jcub.academic_calendar.utils.cons;
import com.jcub.academic_calendar.week_view.week_view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class month_view extends AppCompatActivity {

    final String TAG1 = "MonthView";

    CustomCalendarView calendarView;
    Calendar currentCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize CalendarView from layout
        calendarView = (CustomCalendarView) findViewById(R.id.calendar_view);

        //Initialize calendar with date
        currentCalendar = Calendar.getInstance(Locale.getDefault());

        //Show monday as first date of week
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);

        //Show/hide overflow days of a month
        calendarView.setShowOverflowDate(true);



        //call refreshCalendar to update calendar
        calendarView.refreshCalendar(currentCalendar);


        //Handling custom calendar events
        calendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
               Toast.makeText(month_view.this, df.format(date), Toast.LENGTH_SHORT).show();
                Log.e(TAG1, "date " + date);

                final String[] dateOfdays = getOtherDays(date);
                Intent weekViewInt = new Intent(month_view.this, week_view.class);
                weekViewInt.putExtra("dateOfdays", dateOfdays);
                startActivity(weekViewInt);
                
            }

            @Override
            public void onMonthChanged(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("MM-yyyy");
                Toast.makeText(month_view.this, df.format(date), Toast.LENGTH_SHORT).show();
            }
        });

//        Setting custom font
//final Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Arch_Rival_Bold.ttf");
//        if (null != typeface) {
//            calendarView.setCustomTypeface(typeface);
//            calendarView.refreshCalendar(currentCalendar);
//        }


        //adding calendar day decorators
        List<DayDecorator> decorators = new ArrayList<>();
        decorators.add(new ColorDecorator());
        calendarView.setDecorators(decorators);
        calendarView.refreshCalendar(currentCalendar);
    }

    public String[] getOtherDays(Date selDate) {
        String[] dateOfDays = new String[8];
        switch ((selDate.toString()).split(" ")[0]) {
            case "Mon":
                dateOfDays[0] = selDate.toString();
                dateOfDays[1] = getRequiredDate(selDate, 1);
                dateOfDays[2] = getRequiredDate(selDate, 2);
                dateOfDays[3] = getRequiredDate(selDate, 3);
                dateOfDays[4] = getRequiredDate(selDate, 4);
                dateOfDays[5] = getRequiredDate(selDate, 5);
                dateOfDays[6] = getRequiredDate(selDate, 6);
                dateOfDays[7] = "0";
                break;
            case "Tue":
                dateOfDays[0] = getRequiredDate(selDate, -1);
                dateOfDays[1] = selDate.toString();
                dateOfDays[2] = getRequiredDate(selDate, 1);
                dateOfDays[3] = getRequiredDate(selDate, 2);
                dateOfDays[4] = getRequiredDate(selDate, 3);
                dateOfDays[5] = getRequiredDate(selDate, 4);
                dateOfDays[6] = getRequiredDate(selDate, 5);
                dateOfDays[7] = "1";
                break;
            case "Wed":
                dateOfDays[0] = getRequiredDate(selDate, -2);
                dateOfDays[1] = getRequiredDate(selDate, -1);
                dateOfDays[2] = selDate.toString();
                dateOfDays[3] = getRequiredDate(selDate, 1);
                dateOfDays[4] = getRequiredDate(selDate, 2);
                dateOfDays[5] = getRequiredDate(selDate, 3);
                dateOfDays[6] = getRequiredDate(selDate, 4);
                dateOfDays[7] = "2";
                break;
            case "Thu":
                dateOfDays[0] = getRequiredDate(selDate, -3);
                dateOfDays[1] = getRequiredDate(selDate, -2);
                dateOfDays[2] = getRequiredDate(selDate, -1);
                dateOfDays[3] = selDate.toString();
                dateOfDays[4] = getRequiredDate(selDate, 1);
                dateOfDays[5] = getRequiredDate(selDate, 2);
                dateOfDays[6] = getRequiredDate(selDate, 3);
                dateOfDays[7] = "3";
                break;
            case "Fri":
                dateOfDays[0] = getRequiredDate(selDate, -4);
                dateOfDays[1] = getRequiredDate(selDate, -3);
                dateOfDays[2] = getRequiredDate(selDate, -2);
                dateOfDays[3] = getRequiredDate(selDate, -1);
                dateOfDays[4] = selDate.toString();
                dateOfDays[5] = getRequiredDate(selDate, 1);
                dateOfDays[6] = getRequiredDate(selDate, 2);
                dateOfDays[7] = "4";
                break;
            case "Sat":
                dateOfDays[0] = getRequiredDate(selDate, -5);
                dateOfDays[1] = getRequiredDate(selDate, -4);
                dateOfDays[2] = getRequiredDate(selDate, -3);
                dateOfDays[3] = getRequiredDate(selDate, -2);
                dateOfDays[4] = getRequiredDate(selDate, -1);
                dateOfDays[5] = selDate.toString();
                dateOfDays[6] = getRequiredDate(selDate, 1);
                dateOfDays[7] = "5";
                break;
            case "Sun":
                dateOfDays[0] = getRequiredDate(selDate, -6);
                dateOfDays[1] = getRequiredDate(selDate, -5);
                dateOfDays[2] = getRequiredDate(selDate, -4);
                dateOfDays[3] = getRequiredDate(selDate, -3);
                dateOfDays[4] = getRequiredDate(selDate, -2);
                dateOfDays[5] = getRequiredDate(selDate, -1);
                dateOfDays[6] = selDate.toString();
                dateOfDays[7] = "6";
                break;
        }
        return dateOfDays;
    }

    private String getRequiredDate(Date selDate, int num) {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTime(selDate);
        cal.add(Calendar.DAY_OF_YEAR, num);
       // cons.makeLog("7days before date " + cal.getTime());
        return cal.getTime().toString();
    }

    @Override
    protected void onResume() {
        super.onResume();
        calendarView.refreshCalendar(currentCalendar);
    }

    private class ColorDecorator implements DayDecorator {

        @Override
        public void decorate(DayView cell) {


            cell.setTextColor(Color.WHITE);

            cell.setBackgroundResource(R.drawable.cell_color);


            String[] splitDate = cell.getDate().toString().split(" ");

            if (splitDate[1].equals("Feb") && splitDate[5].equals("2016")) {

                if (splitDate[2].equals("29")) {
                    cell.setBackgroundResource(R.drawable.cell_color);

                }
            }
            if (splitDate[1].equals("Mar") && splitDate[5].equals("2016")) {

                if (1 < Integer.parseInt(splitDate[2]) && Integer.parseInt(splitDate[2]) < 5) {
                    cell.setBackgroundResource(R.drawable.cell_color);;
                }
                if (6 < Integer.parseInt(splitDate[2]) && Integer.parseInt(splitDate[2]) < 10) {
                    cell.setBackgroundResource(R.drawable.orientation_week);
                }

                if (13 < Integer.parseInt(splitDate[2]) && Integer.parseInt(splitDate[2]) < 25) {
                    cell.setBackgroundResource(R.drawable.lectures_period);
                }
                if (splitDate[2].equals("01")) {
                    cell.setBackgroundResource(R.drawable.cell_color);
                }
                if (splitDate[2].equals("10")) {
                    cell.setBackgroundResource(R.drawable.grades_release);
                }
                if (splitDate[2].equals("11")) {
                    cell.setBackgroundResource(R.drawable.lectures_period);
                }
                if (splitDate[2].equals("25")) {
                    cell.setBackgroundResource(R.drawable.public_holidays);
                }
                if (splitDate[2].equals("28")) {
                    cell.setBackgroundResource(R.drawable.public_holidays);
                }
                if (splitDate[2].equals("29")) {
                    cell.setBackgroundResource(R.drawable.tution_fee_payment_due_date);
                }
                if (splitDate[2].equals("30")) {
                    cell.setBackgroundResource(R.drawable.lectures_period);
                }
                if (splitDate[2].equals("31")) {
                    cell.setBackgroundResource(R.drawable.lectures_period);
                }

                if (splitDate[2].equals("05")) {
                    cell.setBackgroundResource(R.drawable.weekends);
                }
                if (splitDate[2].equals("12")) {
                    cell.setBackgroundResource(R.drawable.weekends);
                }
                if (splitDate[2].equals("19")) {
                    cell.setBackgroundResource(R.drawable.weekends);
                }
                if (splitDate[2].equals("26")) {
                    cell.setBackgroundResource(R.drawable.weekends);
                }
                if (splitDate[2].equals("06")) {
                    cell.setBackgroundResource(R.drawable.weekends);
                }
                if (splitDate[2].equals("13")) {
                    cell.setBackgroundResource(R.drawable.weekends);
                }
                if (splitDate[2].equals("20")) {
                    cell.setBackgroundResource(R.drawable.weekends);
                }
                if (splitDate[2].equals("27")) {
                    cell.setBackgroundResource(R.drawable.weekends);
                }

            }


            if (splitDate[1].equals("Apr") && splitDate[5].equals("2016")) {

//                if (01 < Integer.parseInt(splitDate[1]) && Integer.parseInt(splitDate[2]) < 4) {
//                    cell.setBackgroundColor(getResources().getColor(R.color.Light_orange));
//                }

                if (splitDate[2].equals("01")) {
                    cell.setBackgroundResource(R.drawable.lectures_period);
                }
                if (splitDate[2].equals("02")) {
                    cell.setBackgroundResource(R.drawable.weekends);
                }if (splitDate[2].equals("03")) {
                    cell.setBackgroundResource(R.drawable.weekends);
                }
            }

//                if (1 < Integer.parseInt(splitDate[1]) && Integer.parseInt(splitDate[2]) < 4) {
//                    cell.setBackgroundColor(Color.BLUE);
//                }
//                {
//                    {
//                      if (12 < Integer.parseInt(splitDate[2]) && Integer.parseInt(splitDate[2]) < 22){
//                            cell.setBackgroundColor(Color.BLUE);
//                        }
//                    }
//                }

//
//            }



   }
  }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_month_view, menu);
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
}
