package org.fitnesstracker;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles( "test" )
@SpringBootTest
class FitnessTrackerApplicationTest
{

    @Test
    void contextLoads()
    {

    }

    @Test
    void testApplicationStarts()
    {

        FitnessTrackerApplication.main( new String[] {} );
    }
}

