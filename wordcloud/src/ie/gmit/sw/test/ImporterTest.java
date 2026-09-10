package ie.gmit.sw.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ie.gmit.sw.Importer;

public class ImporterTest {

	private String file = "dummy";
	
	@Test
	public void testImportWords()
	{	
		Importer importer = new Importer();
		
		assertNotNull(importer.ImportWords(file));
	}

	@Test
	public void testIsFileOrUrl() 
	{
		Importer importer = new Importer();
		
		assertFalse(importer.isFileOrUrl(file));
	}

	@Test
	public void testIsUrl()
	{
		Importer importer = new Importer();
		
		assertFalse(importer.isUrl(file));
	}

	@Test
	public void testIsFile() 
	{
		Importer importer = new Importer();
		
		assertFalse(importer.isFile(file));
	}

	@Test
	public void testGetWordValueMap()
	{
		Importer importer = new Importer();
		
		importer = new Importer();		
		importer.ImportBlackList(file);
		importer.ImportWords(file);	
		
		assertNotNull(importer.getWordValueMap());
	}

}
