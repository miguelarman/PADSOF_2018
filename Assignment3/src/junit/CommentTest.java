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
		try {
			comm.rateComment(0.0);
			assertEquals(comm.getReplies().size(), 1, 0);
			assertEquals(comm.getAvgRating(), 0.0, 0.1);

			comm.rateComment(1.0);
			assertEquals(comm.getReplies().size(), 2);
			assertEquals(comm.getAvgRating(), 0.5, 0.1);

			comm.rateComment(0.5);
			assertEquals(comm.getReplies().size(), 3);
			assertEquals(comm.getAvgRating(), 0.5, 0.1);

			comm.rateComment(5.0);
			assertEquals(comm.getReplies().size(), 4);
			assertEquals(comm.getAvgRating(), 1.625, 0.1);

			comm.rateComment(2.5);
			assertEquals(comm.getReplies().size(), 5);
			assertEquals(comm.getAvgRating(), 1.8, 0.1);
		} catch (NoUserLoggedException e) {
			fail();
		}
	}
	
	@Test
	public void testAddReply() {		
		try {
			comm.addReply("Pole");

			assertNotNull(comm);
			assertNotNull(comm.getReplies());
			assertEquals(comm.getReplies().size(), 1);

			comm.addReply("First reply");

			assertNotNull(comm);
			assertNotNull(comm.getReplies());
			assertEquals(comm.getReplies().size(), 2);
		} catch (NoUserLoggedException e) {
			fail();
		}
	}
	
	@After
	public void toNull() {
		comm = null;
	}

}
