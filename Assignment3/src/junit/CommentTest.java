package junit;

import static org.junit.Assert.*;

import org.junit.*;

import application.App;
import application.opinion.*;

import exceptions.*;

public class CommentTest {
	
	private Comment comm;
	private App app;
	
	@BeforeClass
	public static void setUpApp() {
		
	}
	
	@Before
	public void setUp() {
		app = App.openApp();
		try {
			app.login("54444111D", "forgetme");
		} catch (UserIsBannedException | IncorrectPasswordException | UnexistentUserException
				| AUserIsAlreadyLoggedException e) {
			fail();
		}
		comm = new Comment("Very nice house");
	}
	
	@Test
	public void testGetAvgRating() {
		Opinion o1 = new Rating((float) 0.0);
		comm.addReply(o1);
		assertEquals(comm.getReplies().size(), 1, 0);
		assertEquals(comm.getAvgRating(), 0.0, 0.1);
		
		Opinion o2 = new Rating((float) 1.0);
		comm.addReply(o2);
		assertEquals(comm.getReplies().size(), 2);
		assertEquals(comm.getAvgRating(), 0.5, 0.1);
		
		Opinion o3 = new Rating((float) 0.5);
		comm.addReply(o3);
		assertEquals(comm.getReplies().size(), 3);
		assertEquals(comm.getAvgRating(), 0.5, 0.1);
		
		Opinion o4 = new Rating((float) 5.0);
		comm.addReply(o4);
		assertEquals(comm.getReplies().size(), 4);
		assertEquals(comm.getAvgRating(), 1.625, 0.1);
		
		Opinion o5 = new Rating((float) 2.5);
		comm.addReply(o5);
		assertEquals(comm.getReplies().size(), 5);
		assertEquals(comm.getAvgRating(), 1.8, 0.1);
	}
	
	@Test
	public void testAddReply() {
		Opinion o = new Comment("First reply");
		
		comm.addReply(o);
		
		assertNotNull(comm);
		assertNotNull(comm.getReplies());
		assertEquals(comm.getReplies().size(), 1);
		
		
		Opinion o2 = new Comment("First reply");
		
		comm.addReply(o2);
		
		assertNotNull(comm);
		assertNotNull(comm.getReplies());
		assertEquals(comm.getReplies().size(), 2);
	}
	
	@After
	public void toNull() {
		comm = null;
	}

}
