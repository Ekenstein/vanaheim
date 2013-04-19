package edu.cth.tmnd.vanaheim.model.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.MessageBuffer;
import edu.cth.tmnd.vanaheim.model.ObjectMapper;
import edu.cth.tmnd.vanaheim.model.ObjectMapper.Type;
import edu.cth.tmnd.vanaheim.model.creatures.monsters.Spider;
import edu.cth.tmnd.vanaheim.model.creatures.player.Player;
import edu.cth.tmnd.vanaheim.model.items.Axe;
import edu.cth.tmnd.vanaheim.model.items.HealthPotion;
import edu.cth.tmnd.vanaheim.model.parser.impl.Handler;

/**
 * Singleton parser that reads a command file with the structure:<br />
 * use * on * : Use<br />
 * eat * : Eat<br />
 *
 * The stars stands for wildcards, this is where objects will match
 * and : Use ... stands for the Handler type that the command uses. <br />
 *
 * E.g. if you type "use crude axe on ogre" it will pick out
 * crude axe and ogre as the wildcards, if they exist in the
 * object mapper that is. It will then check if there
 * are any handlers mapped to "use * on *" and then call
 * the handler method handle(Object[] os) where Object[] os
 * is the wildcards.
 *
 * @author Gabriel Ekblad
 *
 */
final public class Parser {

	private static Parser theInstance = null;

	public static Parser getInstance(final File f) throws IOException {
		if(theInstance == null) {
			theInstance = new Parser(f);
		}

		return theInstance;
	}

	private final static String WILDCARD = "*";
	private final static String TYPE_DEF = ":";
	private final static String HANDLER_POSTFIX = "Handler";

	// Maps commands to handlers
	private final Map<Segment, Handler> actionMap;

	// Objects mapped to strings
	private final ObjectMapper objectMapper;

	private final MessageBuffer messageBuffer;

	// collects prefixes from file. This is to
	// improve the split function.
	private final ArrayList<String> prefixes;

	private Parser(final File commandFile) throws IOException {
		this.actionMap = new HashMap<Segment, Handler>();
		this.objectMapper = ObjectMapper.getInstance();
		this.prefixes = new ArrayList<String>();
		this.messageBuffer = MessageBuffer.getInstance();
		this.readFile(commandFile);
	}

	// must use innerclass for String[] since HashMap doesn't
	// use deepEquals on arrays
	private class Segment {
		private final String[] path;

		public Segment(final String[] path) {
			this.path = path;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + Arrays.hashCode(path);
			return result;
		}

		@Override
		public boolean equals(final Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof Segment)) {
				return false;
			}
			final Segment other = (Segment) obj;
			if (!getOuterType().equals(other.getOuterType())) {
				return false;
			}
			if (!Arrays.deepEquals(path, other.path)) {
				return false;
			}
			return true;
		}

		private Parser getOuterType() {
			return Parser.this;
		}


	}

	private void readFile(final File f) throws FileNotFoundException {
		final Scanner sc = new Scanner(f);

		// since we're gonna use reflection in order of getting
		// the correct handler we will need the package name,
		// and since the handlers are in the same package
		// as the parser, just get the parsers package name.
		final String packageName = this.getClass().getPackage().getName();

		while(sc.hasNextLine()) {
			final String line = sc.nextLine();

			// check if the line is empty. If it is, just skip
			// to the next line.
			if(line.length() <= 0) {
				continue;
			}

			// split by whitespace
			final String[] split = line.split("\\s+");

			// The path without type defs and stuff.
			final String[] path = new String[split.length-2];

			// to hold the raw string for the handler type.
			final StringBuilder handler = new StringBuilder();

			// flag to see if the raw string for the handler is next.
			boolean handlerNext = false;

			for(int i = 0; i < split.length; i++) {
				final String s = split[i];
				if(handlerNext) {
					handler.append(packageName);
					handler.append(".");
					handler.append(s);
					handler.append(HANDLER_POSTFIX);
					break;
				}

				if(s.equals(TYPE_DEF)) {
					handlerNext = true;
					continue;
				}

				// make sure that we don't get an ArrayOutOfBoundsException
				if(i > path.length) {
					break;
				}

				if(!s.equals(WILDCARD)) {
					this.prefixes.add(s);
					path[i] = s;
				} else {
					path[i] = s;
				}
			}

			// check if the parsed line has an handler.
			// ignore this line otherwise.
			if(handler.length() <= 0) {
				continue;
			}

			Handler instance = null;
			try {
				instance = this.getHandlerInstance(handler.toString());
			} catch(final Exception e) {
				continue;
			}

			final Segment segment = new Segment(path);

			if(instance != null) {
				if(!this.actionMap.containsKey(segment)) {
					this.actionMap.put(segment, instance);
				}
			}

		}

		sc.close();
	}

	private Handler getHandlerInstance(final String handler) throws Exception {
		final Class<?> c = Class.forName(handler);

		return (Handler) c.newInstance();
	}

	public void parse(final String command) {
		final String[] tokens = this.split(command.toLowerCase());

		final ArrayList<Object> wildcards = new ArrayList<Object>();

		final String[] path = new String[tokens.length];

		for(int i = 0; i < tokens.length; i++) {
			final String s = tokens[i];
			final Type t = this.objectMapper.isRegistered(s);
			if(t == Type.UNKNOWN) {
				path[i] = s;
			} else {
				path[i] = "*";

				switch(t) {
					case CREATURE:
						wildcards.add(this.objectMapper.getRegisteredCreature(s));
						break;
					case ITEM:
						wildcards.add(this.objectMapper.getRegisteredItem(s));
						break;
					default:
						// will never happen
				}
			}
		}

		final Handler h = this.actionMap.get(new Segment(path));

		final Object[] os = new Object[wildcards.size()];
		wildcards.toArray(os);

		if(h != null) {
			h.handle(os);
		} else {
		}
	}

	private String[] split(final String s) {
		final ArrayList<String> tokens = new ArrayList<String>();
		final StringBuilder token = new StringBuilder();
		final String[] split = s.split("\\s+");

		for(final String str : split) {
			if(this.isPrefix(str)) {
				if(tokens.size() > 0) {
					if(token.length() > 0) {
						final String wildcard = token.toString().trim();
						token.setLength(0);
						tokens.add(wildcard);
					}

					tokens.add(str);
				} else {
					tokens.add(str);
				}
			} else {
				token.append(str);
				token.append(" ");
			}
		}

		if(token.length() > 0) {
			final String rest = token.toString().trim();
			tokens.add(rest);
		}

		final String[] arraySplit = new String[tokens.size()];
		tokens.toArray(arraySplit);

		return arraySplit;
	}

	private boolean isPrefix(final String s) {
		return this.prefixes.contains(s);
	}

	public static void main(final String[] args) {
		try {
			final Player owner = new Player(32f, 32f, 300, new Inventory(20), 100, "Harald");
			final Spider target = new Spider(32f, 32f, 300, new Inventory(2), 100, "Spider");
			final Axe axe = new Axe(owner);
			final HealthPotion pot = new HealthPotion(owner);

			final ObjectMapper om = ObjectMapper.getInstance();
			om.registerCreature(target);
			om.registerItem(axe);
			om.registerItem(pot);

			String command = "use crude axe on spider";

			long startTime = System.nanoTime();
			final Parser p = Parser.getInstance(new File("data/commands"));
			long endTime = System.nanoTime();

			System.out.println("It took " + (endTime - startTime)/1000000000.0 + " seconds to initiate the Parser");

			startTime = System.nanoTime();
			p.parse(command);
			endTime = System.nanoTime();

			System.out.println("It took " + (endTime - startTime)/1000000000.0 + " seconds to parse " + command);

			owner.damage(20);

			command = "use healing potion";
			startTime = System.nanoTime();
			p.parse(command);
			endTime = System.nanoTime();

			System.out.println("It took " + (endTime - startTime)/1000000000.0 + " seconds to parse " + command);

		} catch(final Exception e) {

		}
	}
}