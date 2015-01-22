package org.hullhul.spaceduell;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class ResUtilTest {
	
	@Test public void loadImage() throws IOException {
		assertThat(ResUtil.readImage("ship1.png")).isNotNull();
	}

}
