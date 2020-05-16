/**
 *  @author Rajat Sahu 
 *  https://github.com/Rjtsahu/object-csv-java
 *  ObjectCsv Java Library : Handy library to map CSV document to java model. 
 * */
package com.iron.objectcsv;

import com.iron.objectcsv.annotations.CsvColumn;
import com.iron.objectcsv.annotations.CsvModel;
import com.iron.objectcsv.annotations.CsvParameter;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Dictionary;
import java.util.Date;

/**
 * This class will help in handling generic object by using JAVA reflection
 * apis. This class will help in assigning values to given property of generic
 * model from given dictionary having common propertyName or annotation
 * indicating property name.
 */
final class GenericModelAdapter<T> {

	/**
	 * variable holding instance of class generic class T.
	 */
	private T classInstance;

	/**
	 * Dictionary that holds key/value pair of data.
	 **/
	private Dictionary<String, String> dictionary;

	/**
	 * Default date format in CSV, in future make it configurable by user input.
	 */
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	public GenericModelAdapter(Class<T> classGeneric, Dictionary<String, String> dictionary) throws ObjectCsvException {
		this(classGeneric);
		this.dictionary = dictionary;
	}

	GenericModelAdapter(Class<T> classGeneric) throws ObjectCsvException {
		try {
			classInstance = classGeneric.newInstance();
		} catch (IllegalAccessException | InstantiationException e) {
			throw new ObjectCsvException("Exception in creating instance : " + e.getMessage());
		}
	}

	/**
	 * Primary method to map generic object to dictionary
	 * 
	 * @param dictionary header-value pair.
	 * @return Object T after mapping with dictionary.
	 */
	T MapDictionaryToObject(Dictionary<String, String> dictionary) {
		this.dictionary = dictionary;
		// complete logic to map
		try {
			PopulateModelFromDictionary();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return classInstance;
	}

	/**
	 * This method will assign every field in classInstance with same key in
	 * dictionary (if exists), also converts to proper dataType as declared in class
	 * field.
	 */
	private void PopulateModelFromDictionary() throws IllegalArgumentException, IllegalAccessException {

		CsvModel csvAnnotation = classInstance.getClass().getAnnotation(CsvModel.class);
		if (csvAnnotation == null)
			return;
		boolean useColumnIndexing = !csvAnnotation.headerPresent();

		for (Field field : classInstance.getClass().getFields()) {

			String fieldId = getFieldIdToSearchInDictionary(field, useColumnIndexing);
			if (fieldId == null)
				continue;

			String fieldValue = dictionary.get(fieldId);
			if (fieldValue == null)
				continue;

			AssignValue(field, fieldValue);
		}

	}

	/**
	 * Get the data type of given field and tries to convert fieldValue to that
	 * type. Currently int,bool,double,string and date data-types are available.
	 * 
	 * @param field      Field of class.
	 * @param fieldValue value to be assigned to field.
	 */
	private void AssignValue(Field field, String fieldValue) {

		try {
			if (field.getType() == int.class) {
				field.set(classInstance, Integer.valueOf(fieldValue));
			} else if (field.getType() == boolean.class) {
				field.set(classInstance, Boolean.valueOf(fieldValue));
			} else if (field.getType() == double.class) {
				field.set(classInstance, Double.valueOf(fieldValue));
			} else if (field.getType() == Date.class) {
				DateFormat df = new SimpleDateFormat(DATE_FORMAT);
				field.set(classInstance, df.parse(fieldValue));
			} else {
				field.set(classInstance, fieldValue);
			}
		} catch (Exception e) {
			System.err.println(
					"Unable to parse " + fieldValue + " to type " + field.getType() + " | Reason: " + e.getMessage());
		}
	}

	/**
	 * Get key associated with field by looking it in {@link CsvParameter}
	 * annotation on thatv filed,if columnIndexing is used then look into
	 * {@link CsvColumn} annotation
	 * 
	 * @param field             field of class.
	 * @param useColumnIndexing weather to use column indexing,will be true when
	 *                          there is no header row present in CSV but it is
	 *                          input provided by user.
	 * @return string representing key.
	 */
	private String getFieldIdToSearchInDictionary(final Field field, boolean useColumnIndexing) {

		String keyToSearch;
		if (useColumnIndexing) {
			CsvColumn csvParamAnnotation = field.getAnnotation(CsvColumn.class);
			if (csvParamAnnotation == null)
				return null;
			keyToSearch = String.valueOf(csvParamAnnotation.columnIndex());
		} else {
			CsvParameter csvParamAnnotation = field.getAnnotation(CsvParameter.class);
			if (csvParamAnnotation == null)
				return null;
			keyToSearch = String.valueOf(csvParamAnnotation.value());
		}
		return keyToSearch.trim();
	}
}
