package com.example.registration;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    public interface OnStudentActionListener {
        void onEdit(Student student);
        void onDelete(Student student);
    }

    private ArrayList<Student> students;
    private OnStudentActionListener listener;

    public StudentAdapter(ArrayList<Student> students, OnStudentActionListener listener) {
        this.students = students;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student s = students.get(position);
        holder.tvName.setText(s.getFullName());
        holder.tvDob.setText(s.getDob());
        holder.tvGender.setText(s.getGender());
        holder.tvContact.setText(s.getContact());
        holder.tvEmail.setText(s.getEmail());

        holder.itemView.findViewById(R.id.btnMenu).setOnClickListener(view -> {
            PopupMenu popup = new PopupMenu(view.getContext(), view);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.student_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.action_edit) {
                    listener.onEdit(s);
                    return true;
                } else if (item.getItemId() == R.id.action_delete) {
                    listener.onDelete(s);
                    return true;
                }
                return false;
            });
            popup.show();
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDob, tvGender, tvContact, tvEmail;

        StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDob = itemView.findViewById(R.id.tvDob);
            tvGender = itemView.findViewById(R.id.tvGender);
            tvContact = itemView.findViewById(R.id.tvContact);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }
}