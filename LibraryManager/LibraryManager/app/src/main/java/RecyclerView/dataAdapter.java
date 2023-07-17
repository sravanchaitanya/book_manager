package RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarymanager.R;

import java.util.ArrayList;

import Activities.Book;

public class dataAdapter extends RecyclerView.Adapter<dataAdapter.ViewHolder> {
    private final Context parent;
    private ArrayList<Book> books = new ArrayList<Book>();
    public dataAdapter(Context parent) {
        this.parent = parent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = books.get(position);
        holder.name.setText(book.getName());
        holder.author.setText(book.getAuthor());
        holder.genre.setText(book.getGenre());
        holder.price.setText(book.getPrice());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
    public void setBooks(ArrayList<Book> arr){
        this.books = arr;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView author;
        public TextView genre;
        public TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            author = itemView.findViewById(R.id.author);
            genre = itemView.findViewById(R.id.genre);
            price = itemView.findViewById(R.id.price);
        }
    }
}
