package android.abc.nl.abccc.activity;

import android.abc.nl.abccc.R;
import android.abc.nl.abccc.fragments.ArticleFragment;
import android.abc.nl.abccc.fragments.HeadlinesFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class LoginActivity extends AppCompatActivity implements HeadlinesFragment.OnHeadlineSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_articles);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle app bar item clicks here. The app bar
        // automatically handles clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override
    public void onArticleSelected(int position) {
        ArticleFragment newFragment = new ArticleFragment();
        Bundle args = new Bundle();
        args.putInt(ArticleFragment.ARG_POSITION, position);
        newFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, newFragment);
        transaction.commit();

        findViewById(R.id.container).setVisibility(View.VISIBLE);
        findViewById(R.id.calendar).setVisibility(View.GONE);

    }

    @Override
    public void onBackPressed() {
        if (findViewById(R.id.container).getVisibility() == View.VISIBLE) {
            findViewById(R.id.container).setVisibility(View.GONE);
            findViewById(R.id.calendar).setVisibility(View.VISIBLE);
            return;
        }
        super.onBackPressed();
    }
}

