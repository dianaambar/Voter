package voter.main;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class ViewingStats extends Activity implements OnClickListener{

	private static final String TAG = "ViewingStats";

	//Buttons
	private Button helpBtn; 
	
	//Fields to enter information about this question
	private EditText titleField; 
	private EditText ansField; 
	private EditText entryField; 
	
	// field of the id, uneditable, int in that field
	private EditText questionIdField; 
	
	//Adapter to Handle data
	private ArrayAdapter<String> adapter;
	
	//View that contains the list of possible answers
	private ListView ansList;  

	@Override
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewingstats);
		
		//helpBtn = (Button) findViewById(R.id.helpBtn);
		//helpBtn.setOnClickListener(this);
		
		//Get the bundle
		Bundle extras = getIntent().getExtras(); 
		
		String title = extras.getString("Title");
		String question = extras.getString("Question");
		
		//Toast ts = Toast.makeText(this, title, Toast.LENGTH_SHORT);
		//ts.show();
		
		//This might be the worse thing I have ever done FIXME
		List<String> possibleResponse = Arrays.asList(extras.getString("PossibleResponse").split(","));

		//Parse the list to remove ugly things
		for(int i = 0; i < possibleResponse.size(); i++){
			String noClean;
			
			if(i == 0){
			    noClean = possibleResponse.get(0);
				noClean = noClean.substring(4);
				possibleResponse.set(0, noClean);
				continue;
			}
			
			if(i == possibleResponse.size() - 1){
			    noClean = possibleResponse.get(possibleResponse.size() - 1);
				noClean = noClean.substring(4, noClean.length() - 1);
				possibleResponse.set(possibleResponse.size() - 1, noClean);
				continue;
			}
			
			noClean = possibleResponse.get(i);
			noClean = noClean.substring(3);
			possibleResponse.set(i, noClean);
			
		}
		
		String id = extras.getString("ID");
		
		//Fill the fields up 
		titleField = (EditText) findViewById(R.id.titleField);
		entryField = (EditText) findViewById(R.id.entryField);
		ansField = (EditText) findViewById(R.id.possibleAnsField);
		questionIdField = (EditText) findViewById(R.id.questionIdField);
		
		titleField.setText(title);
		titleField.setFocusable(false); 	
		
		entryField.setText(question);
		entryField.setFocusable(false); 	
		
		questionIdField.setText(id);
		questionIdField.setFocusable(false); 	
		
		ansList = (ListView) findViewById(R.id.listResponses); 
		
		//Setup List stuff
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				possibleResponse);
		ansList.setAdapter(adapter); 
		 
	}

	public void onClick(View v) {
		
		switch (v.getId()) {

		// Get some help
		case R.id.helpBtn:
			Intent help = new Intent(this, Help.class);
			startActivity(help);
			break;

		default:
			Log.e(TAG, "onClick id not found");
		}
	}
	
}
