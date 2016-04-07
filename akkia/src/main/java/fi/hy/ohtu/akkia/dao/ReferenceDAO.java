package fi.hy.ohtu.akkia.dao;

import java.util.List;

import fi.hy.ohtu.akkia.Reference;

public interface ReferenceDAO {
	public void save() throws Exception;
	public void load() throws Exception;
	public void addReference(Reference ref);
	public List<Reference> getAllReferences();
}
