package softuni.exam.readFile;
//TestVolcanologistServiceReadFromFile

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam.service.impl.VolcanologistServiceImpl;

import java.io.IOException;

//TODO to set tests in judge!!!!!!!!!!!!!!!


@ExtendWith(MockitoExtension.class)
public class TestVolcanologistServiceReadFromFile {

    @InjectMocks
    private VolcanologistServiceImpl volcanologistService;

    @Test
    void readAstronomersFromFile() throws IOException {
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<volcanologists>\n" +
                "    <volcanologist>\n" +
                "        <first_name>John</first_name>\n" +
                "        <last_name>Doe</last_name>\n" +
                "        <salary>5000.00</salary>\n" +
                "        <age>55</age>\n" +
                "        <exploring_from>1987-01-15</exploring_from>\n" +
                "        <exploring_volcano_id>11</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Jane</first_name>\n" +
                "        <last_name>Smith</last_name>\n" +
                "        <salary>5500.00</salary>\n" +
                "        <age>60</age>\n" +
                "        <exploring_from>1982-05-20</exploring_from>\n" +
                "        <exploring_volcano_id>20</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Michael</first_name>\n" +
                "        <last_name>Johnson</last_name>\n" +
                "        <salary>4800.00</salary>\n" +
                "        <age>58</age>\n" +
                "        <exploring_from>1984-09-10</exploring_from>\n" +
                "        <exploring_volcano_id>33</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Michael</first_name>\n" +
                "        <last_name>Johnson</last_name>\n" +
                "        <salary>4800.00</salary>\n" +
                "        <age>58</age>\n" +
                "        <exploring_from>1984-09-10</exploring_from>\n" +
                "        <exploring_volcano_id>33</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Emily</first_name>\n" +
                "        <last_name>Brown</last_name>\n" +
                "        <salary>5200.00</salary>\n" +
                "        <age>56</age>\n" +
                "        <exploring_from>1986-11-25</exploring_from>\n" +
                "        <exploring_volcano_id>34</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Daniel</first_name>\n" +
                "        <last_name>Martinez</last_name>\n" +
                "        <salary>5100.00</salary>\n" +
                "        <age>57</age>\n" +
                "        <exploring_from>1985-03-30</exploring_from>\n" +
                "        <exploring_volcano_id>15</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Sarah</first_name>\n" +
                "        <last_name>Taylor</last_name>\n" +
                "        <salary>4900.00</salary>\n" +
                "        <age>49</age>\n" +
                "        <exploring_from>1983-07-05</exploring_from>\n" +
                "        <exploring_volcano_id>26</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Matthew</first_name>\n" +
                "        <last_name>Anderson</last_name>\n" +
                "        <salary>5300.00</salary>\n" +
                "        <age>54</age>\n" +
                "        <exploring_from>1988-02-18</exploring_from>\n" +
                "        <exploring_volcano_id>17</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Jessica</first_name>\n" +
                "        <last_name>Wilson</last_name>\n" +
                "        <salary>5400.00</salary>\n" +
                "        <age>33</age>\n" +
                "        <exploring_from>2003-06-12</exploring_from>\n" +
                "        <exploring_volcano_id>8</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Christopher</first_name>\n" +
                "        <last_name>Thompson</last_name>\n" +
                "        <salary>4700.00</salary>\n" +
                "        <age>32</age>\n" +
                "        <exploring_from>2004-10-08</exploring_from>\n" +
                "        <exploring_volcano_id>39</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Amanda</first_name>\n" +
                "        <last_name>Garcia</last_name>\n" +
                "        <salary>4600.00</salary>\n" +
                "        <age>31</age>\n" +
                "        <exploring_from>2012-04-14</exploring_from>\n" +
                "        <exploring_volcano_id>16</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>David</first_name>\n" +
                "        <last_name>Hernandez</last_name>\n" +
                "        <salary>4700.00</salary>\n" +
                "        <age>30</age>\n" +
                "        <exploring_from>2013-08-20</exploring_from>\n" +
                "        <exploring_volcano_id>21</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Ashley</first_name>\n" +
                "        <last_name>Lopez</last_name>\n" +
                "        <salary>5200.00</salary>\n" +
                "        <age>49</age>\n" +
                "        <exploring_from>1993-12-25</exploring_from>\n" +
                "        <exploring_volcano_id>12</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>James</first_name>\n" +
                "        <last_name>Gonzalez</last_name>\n" +
                "        <salary>5100.00</salary>\n" +
                "        <age>28</age>\n" +
                "        <exploring_from>2020-01-30</exploring_from>\n" +
                "        <exploring_volcano_id>33</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Jennifer</first_name>\n" +
                "        <last_name>Rodriguez</last_name>\n" +
                "        <salary>5000.00</salary>\n" +
                "        <age>27</age>\n" +
                "        <exploring_from>2015-07-05</exploring_from>\n" +
                "        <exploring_volcano_id>24</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Ryan</first_name>\n" +
                "        <last_name>Perez</last_name>\n" +
                "        <salary>4900.00</salary>\n" +
                "        <age>26</age>\n" +
                "        <exploring_from>2020-11-10</exploring_from>\n" +
                "        <exploring_volcano_id>25</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Nicole</first_name>\n" +
                "        <last_name>Sanchez</last_name>\n" +
                "        <salary>4800.00</salary>\n" +
                "        <age>25</age>\n" +
                "        <exploring_from>2017-03-15</exploring_from>\n" +
                "        <exploring_volcano_id>36</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Kevin</first_name>\n" +
                "        <last_name>Ramirez</last_name>\n" +
                "        <salary>5300.00</salary>\n" +
                "        <age>54</age>\n" +
                "        <exploring_from>1998-05-20</exploring_from>\n" +
                "        <exploring_volcano_id>13</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Elizabeth</first_name>\n" +
                "        <last_name>Torres</last_name>\n" +
                "        <salary>5200.00</salary>\n" +
                "        <age>43</age>\n" +
                "        <exploring_from>1999-09-25</exploring_from>\n" +
                "        <exploring_volcano_id>10</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>William</first_name>\n" +
                "        <last_name>Flores</last_name>\n" +
                "        <salary>4700.00</salary>\n" +
                "        <age>32</age>\n" +
                "        <exploring_from>2000-12-30</exploring_from>\n" +
                "        <exploring_volcano_id>31</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Megan</first_name>\n" +
                "        <last_name>Rivera</last_name>\n" +
                "        <salary>5400.00</salary>\n" +
                "        <age>41</age>\n" +
                "        <exploring_from>2001-02-10</exploring_from>\n" +
                "        <exploring_volcano_id>29</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Jacob</first_name>\n" +
                "        <last_name>Campbell</last_name>\n" +
                "        <salary>4800.00</salary>\n" +
                "        <age>53</age>\n" +
                "        <exploring_from>1989-11-15</exploring_from>\n" +
                "        <exploring_volcano_id>22</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Olivia</first_name>\n" +
                "        <last_name>Carter</last_name>\n" +
                "        <salary>5500.00</salary>\n" +
                "        <age>69</age>\n" +
                "        <exploring_from>1993-08-20</exploring_from>\n" +
                "        <exploring_volcano_id>5</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Liam</first_name>\n" +
                "        <last_name>Hill</last_name>\n" +
                "        <salary>5200.00</salary>\n" +
                "        <age>71</age>\n" +
                "        <exploring_from>1991-06-25</exploring_from>\n" +
                "        <exploring_volcano_id>4</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Emma</first_name>\n" +
                "        <last_name>Yung</last_name>\n" +
                "        <salary>5000.00</salary>\n" +
                "        <age>28</age>\n" +
                "        <exploring_from>2014-04-30</exploring_from>\n" +
                "        <exploring_volcano_id>4</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Mason</first_name>\n" +
                "        <last_name>Green</last_name>\n" +
                "        <salary>5300.00</salary>\n" +
                "        <age>30</age>\n" +
                "        <exploring_from>2021-02-05</exploring_from>\n" +
                "        <exploring_volcano_id>8</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Sophia</first_name>\n" +
                "        <last_name>King</last_name>\n" +
                "        <salary>4900.00</salary>\n" +
                "        <age>32</age>\n" +
                "        <exploring_from>2000-01-10</exploring_from>\n" +
                "        <exploring_volcano_id>16</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Peter</first_name>\n" +
                "        <last_name>Evans</last_name>\n" +
                "        <salary>4700.00</salary>\n" +
                "        <age>27</age>\n" +
                "        <exploring_from>2022-07-15</exploring_from>\n" +
                "        <exploring_volcano_id>7</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Isabella</first_name>\n" +
                "        <last_name>Scott</last_name>\n" +
                "        <salary>5100.00</salary>\n" +
                "        <age>46</age>\n" +
                "        <exploring_from>1996-09-20</exploring_from>\n" +
                "        <exploring_volcano_id>1</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Ethan</first_name>\n" +
                "        <last_name>Adams</last_name>\n" +
                "        <salary>4600.00</salary>\n" +
                "        <age>25</age>\n" +
                "        <exploring_from>2020-11-25</exploring_from>\n" +
                "        <exploring_volcano_id>3</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Amelia</first_name>\n" +
                "        <last_name>Baker</last_name>\n" +
                "        <salary>5400.00</salary>\n" +
                "        <age>24</age>\n" +
                "        <exploring_from>2019-03-30</exploring_from>\n" +
                "        <exploring_volcano_id>9</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Alexander</first_name>\n" +
                "        <last_name>Cook</last_name>\n" +
                "        <salary>4700.00</salary>\n" +
                "        <age>23</age>\n" +
                "        <exploring_from>2023-05-05</exploring_from>\n" +
                "        <exploring_volcano_id>38</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Marta</first_name>\n" +
                "        <last_name>Gonzales</last_name>\n" +
                "        <salary>5200.00</salary>\n" +
                "        <age>42</age>\n" +
                "        <exploring_from>2000-07-10</exploring_from>\n" +
                "        <exploring_volcano_id>6</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Samantha</first_name>\n" +
                "        <last_name>Nelson</last_name>\n" +
                "        <salary>5100.00</salary>\n" +
                "        <age>29</age>\n" +
                "        <exploring_from>2001-09-15</exploring_from>\n" +
                "        <exploring_volcano_id>17</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Benjamin</first_name>\n" +
                "        <last_name>Mitchell</last_name>\n" +
                "        <salary>5000.00</salary>\n" +
                "        <age>74</age>\n" +
                "        <exploring_from>1988-10-20</exploring_from>\n" +
                "        <exploring_volcano_id>14</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Chloe</first_name>\n" +
                "        <last_name>Roberts</last_name>\n" +
                "        <salary>4900.00</salary>\n" +
                "        <age>49</age>\n" +
                "        <exploring_from>1992-12-25</exploring_from>\n" +
                "        <exploring_volcano_id>40</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Dani</first_name>\n" +
                "        <last_name>Leen</last_name>\n" +
                "        <salary>4800.00</salary>\n" +
                "        <age>28</age>\n" +
                "        <exploring_from>2014-03-30</exploring_from>\n" +
                "        <exploring_volcano_id>40</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Ava</first_name>\n" +
                "        <last_name>Walker</last_name>\n" +
                "        <salary>5300.00</salary>\n" +
                "        <age>27</age>\n" +
                "        <exploring_from>2018-05-05</exploring_from>\n" +
                "        <exploring_volcano_id>22</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Madison</first_name>\n" +
                "        <last_name>Hall</last_name>\n" +
                "        <salary>5200.00</salary>\n" +
                "        <age>66</age>\n" +
                "        <exploring_from>1996-07-10</exploring_from>\n" +
                "        <exploring_volcano_id>23</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Patrick</first_name>\n" +
                "        <last_name>Lewis</last_name>\n" +
                "        <salary>4700.00</salary>\n" +
                "        <age>55</age>\n" +
                "        <exploring_from>1997-09-15</exploring_from>\n" +
                "        <exploring_volcano_id>2</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "    <volcanologist>\n" +
                "        <first_name>Abigail</first_name>\n" +
                "        <last_name>Young</last_name>\n" +
                "        <salary>5400.00</salary>\n" +
                "        <age>24</age>\n" +
                "        <exploring_from>2021-11-20</exploring_from>\n" +
                "        <exploring_volcano_id>18</exploring_volcano_id>\n" +
                "    </volcanologist>\n" +
                "</volcanologists>";


        String actual = volcanologistService.readVolcanologistsFromFile()
                .replaceAll("\\r\\n|\\r|\\n", "\n").trim();


        Assertions.assertEquals(expected, actual);
    }
}