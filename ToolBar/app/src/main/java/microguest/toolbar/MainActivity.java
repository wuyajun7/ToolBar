package microguest.toolbar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    private CToolBar ctoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ctoolbar = (CToolBar) findViewById(R.id.ctoolbar);
        ctoolbar.setOnToolBarClickListener(new CToolBar.OnToolBarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(MainActivity.this, "LEFT", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(MainActivity.this, "RIGHT", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
