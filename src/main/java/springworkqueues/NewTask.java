package springworkqueues;

//import org.springframework.amqp.core.AmqpAdmin;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class NewTask {

	private static final String TASK_QUEUE_NAME = "task_queue";

	public static void main(String[] argv) throws Exception {
		
//		ConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
//		AmqpAdmin admin = new RabbitAdmin(connectionFactory);
//		admin.declareQueue(new Queue(TASK_QUEUE_NAME));
//
//		AmqpTemplate template = new RabbitTemplate(connectionFactory);
//
//		String message = getMessage(argv);
//		template.convertAndSend(TASK_QUEUE_NAME, message);
//
//		System.out.println(" [x] Sent '" + message + "'");

	}

	private static String getMessage(String[] strings) {
		if (strings.length < 1)
			return "Hello World!";
		return joinStrings(strings, " ");
	}

	private static String joinStrings(String[] strings, String delimiter) {
		int length = strings.length;
		if (length == 0)
			return "";
		StringBuilder words = new StringBuilder(strings[0]);
		for (int i = 1; i < length; i++) {
			words.append(delimiter).append(strings[i]);
		}
		return words.toString();
	}
}
