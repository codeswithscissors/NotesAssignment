package notes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotesController {

    private final AtomicLong counter = new AtomicLong();
    private HashMap<Long, Note> notes = new HashMap<Long, Note>();
    
    @RequestMapping(value="/api/notes", method=RequestMethod.POST)
    public Note createNote(@RequestBody RequestWrapper wrapper) {
        Note note = new Note(counter.incrementAndGet(), wrapper.getBody());
        notes.put(note.getId(), note);
        return note;
    }
    
    @RequestMapping(value="/api/notes/{id}", method=RequestMethod.GET)
    public Note getNote(@PathVariable("id") Long id){
    	return notes.get(id);
    }
    
    @RequestMapping(value="/api/notes", method=RequestMethod.GET)
    public List<Note> searchNotes(@RequestParam(value="query", required=false) String query){
    	List<Note> matchingNotes = new ArrayList<Note>();
    	
    	//If there is a search term, search notes. Otherwise return them all
    	if (query != null && !query.isEmpty()){
    		for (Map.Entry<Long, Note> entry : notes.entrySet()){
    			if (entry.getValue().getBody().contains(query)){
    				matchingNotes.add(entry.getValue());
    			}
    		}
    	} else {
    		matchingNotes.addAll(notes.values());
    	}
    	return matchingNotes;
    }
}