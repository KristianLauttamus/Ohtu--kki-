package fi.hy.ohtu.akkia.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import fi.hy.ohtu.akkia.Reference;
import fi.hy.ohtu.akkia.ReferenceList;

public class ReferenceDAOJsonImpl implements ReferenceDAO {
	private String filename;
	private List<Reference> references;
	
	public ReferenceDAOJsonImpl(String filename) {
		this.filename = filename;
		this.references = new ArrayList<Reference>();
	}
	
	public void save() throws Exception {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		ReferenceList list = new ReferenceList(this.references);
		String json = gson.toJson(list);
		FileUtils.writeStringToFile(new File(filename), json);
	}

	public void load() throws Exception {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String data = FileUtils.readFileToString(new File(filename));
		ReferenceList list = gson.fromJson(data, ReferenceList.class);
		this.references = list.getReferences();
	}

	public List<Reference> getAllReferences() {
		return references;
	}

	public void addReference(Reference ref) {
		references.add(ref);
	}

}
