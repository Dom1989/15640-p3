package dfs;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import message.*;

public class CommunicationModule {

	/**
	 * send the Message to the IP and Port specified in msg DesIP and DesPort
	 * @param msg
	 * @return
	 */
	public static Message sendMessage(Message msg) throws UnknownHostException, IOException,
			ClassNotFoundException {
		Message reply = null;
		Socket socket = null;
		ObjectOutputStream output = null;
		ObjectInputStream input = null;

		/* open socket talk to the receiver */
		socket = new Socket(msg.getDesIP(), msg.getDesPort());
		output = new ObjectOutputStream(socket.getOutputStream());
		output.writeObject(msg);
		output.flush();

		input = new ObjectInputStream(socket.getInputStream());
		reply = (Message) input.readObject();
		return reply;
	}
	
	/**
	 * send the Message to the InputStream specified at args
	 * @param input
	 * @param output
	 * @param msg
	 * @return
	 */
	public static Message sendMessage(InputStream input, OutputStream output, Message msg)
			throws UnknownHostException, IOException, ClassNotFoundException {
		Message reply = null;
		ObjectOutputStream objOutput = new ObjectOutputStream(output);

		objOutput.writeObject(msg);
		objOutput.flush();

		ObjectInputStream objInput = new ObjectInputStream(input);
		reply = (Message) objInput.readObject();
		return reply;
	}
}
