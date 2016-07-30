package com.daryl.nytimessearch.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.daryl.nytimessearch.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FilterSearchDialogFragment extends DialogFragment implements CalendarDatePickerDialogFragment.OnDateSetListener {

    private static final String FRAG_TAG_DATE_PICKER = "Date Picker";

    private EditText etFilterDate;
    private Spinner spSortOrder;
    private CheckBox cbArts;
    private CheckBox cbFashionAndStyle;
    private CheckBox cbSports;
    private Button btnSave;

    public interface FilterSearchDialogListener {

        void onFinishFilterSearchDialog(String beginDate, String sortOrder, boolean isArts,
                                        boolean isFashionAndStyle, boolean isSports);
    }
    public FilterSearchDialogFragment() {
        // Required empty public constructor
    }

    public static FilterSearchDialogFragment newInstance(String title) {
        FilterSearchDialogFragment fragment = new FilterSearchDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filter_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpDatePickerEditText(view);
        setUpSortOrderSpinner(view);
        setUpCheckboxes(view);
        setUpSaveButton(view);
    }

    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear,
                          int dayOfMonth) {
        etFilterDate.setText(new SimpleDateFormat("MM/dd/yy").format(new Date(year,
                monthOfYear, dayOfMonth)));
    }

    private void setUpDatePickerEditText(View view) {
        etFilterDate = (EditText) view.findViewById(R.id.etFilterDate);
        etFilterDate.setText(new SimpleDateFormat("MM/dd/yy").format(new Date()));
        etFilterDate.setInputType(InputType.TYPE_NULL);
        etFilterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment().
                        setOnDateSetListener(FilterSearchDialogFragment.this);
                cdp.show(getChildFragmentManager(), FRAG_TAG_DATE_PICKER);
            }
        });

        etFilterDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment().
                            setOnDateSetListener(FilterSearchDialogFragment.this);
                    cdp.show(getChildFragmentManager(), FRAG_TAG_DATE_PICKER);
                }
            }
        });
    }

    private void setUpSortOrderSpinner(View view) {
        spSortOrder = (Spinner) view.findViewById(R.id.spSortOrder);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.sort_order_arrays, android.R.layout.simple_spinner_dropdown_item);
        spSortOrder.setAdapter(spinnerAdapter);
        if (getArguments().getString("sortOrder") != null) {
            setSpinnerToValue(spSortOrder, getArguments().getString("sortOrder"));
        }
    }

    private void setSpinnerToValue(Spinner spinner, String value) {
        int index = 0;
        SpinnerAdapter adapter = spinner.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).equals(value)) {
                index = i;
                break;
            }
        }
        spinner.setSelection(index);
    }

    private void setUpCheckboxes(final View view) {
        cbArts = (CheckBox) view.findViewById(R.id.cbArts);
        cbFashionAndStyle = (CheckBox) view.findViewById(R.id.cbFashionAndStyle);
        cbSports = (CheckBox) view.findViewById(R.id.cbSports);
    };

    private void setUpSaveButton(View view) {
        btnSave = (Button) view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSave();
            }
        });
    }

    private void onSave() {
        FilterSearchDialogListener listener = (FilterSearchDialogListener) getActivity();
        String sortOrderValue = spSortOrder.getSelectedItem().toString();
        boolean isArts = cbArts.isChecked();
        boolean isFashionAndStyle = cbFashionAndStyle.isChecked();
        boolean isSports = cbSports.isChecked();
        listener.onFinishFilterSearchDialog(dateValue(), sortOrderValue, isArts, isFashionAndStyle,
                isSports);
        dismiss();
    }

    private String dateValue() {
        String dueDate = "";
        try {
            DateFormat originalFormat = new SimpleDateFormat("MM/dd/yy");
            DateFormat targetFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = originalFormat.parse(etFilterDate.getText().toString());
            dueDate = targetFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dueDate;
    }
}
