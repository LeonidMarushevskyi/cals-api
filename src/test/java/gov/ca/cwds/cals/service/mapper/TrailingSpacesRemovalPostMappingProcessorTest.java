package gov.ca.cwds.cals.service.mapper;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.Set;
import org.junit.Test;

public class TrailingSpacesRemovalPostMappingProcessorTest {

    @Test
    public void trailingSpacesRemovalTest() {
        DeepObject insideCollectionDeepObject = new DeepObject();
        insideCollectionDeepObject.setString("insideCollectionDeepObject    ");
        Set<DeepObject> deepObjectList = Collections.singleton(insideCollectionDeepObject);

        DeepObject deepObject = new DeepObject();
        deepObject.setString("deepObject     ");
        deepObject.setDeepObjectsList(deepObjectList);
        RootObject rootObject = new RootObject();
        rootObject.setDeepObject(deepObject);
        rootObject.setInteger(1);
        rootObject.setNullString(null);
        rootObject.setRootString("rootString     ");
        new TrailingSpacesRemovalPostMappingProcessor().apply(rootObject);
        assertEquals("deepObject", deepObject.getString());
        assertEquals("rootString", rootObject.getRootString());
        assertEquals("insideCollectionDeepObject", insideCollectionDeepObject.getString());
    }

}