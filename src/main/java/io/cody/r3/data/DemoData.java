package io.cody.r3.data;

import io.cody.r3.domain.Message;
import io.cody.r3.repos.MessageRepository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Because this class implements CommandLineRunner, the run method is executed as soon as the server
 * successfully starts and before it begins accepting requests from the outside. Here, we use this
 * as a place to run some code that generates and saves a list of random products into the
 * database.
 */
@Component
public class DemoData implements CommandLineRunner {

  @Autowired
  private Environment env;

  // Repositories
  @Autowired
  private MessageRepository messageRepository;

  @Override
  public void run(String... args) throws Exception {
    boolean loadData;

    try {
      loadData = Boolean.parseBoolean(env.getProperty("products.load"));
    } catch (NumberFormatException nfe) {
      loadData = false;
    }

    if (loadData) {
      try {
        seedDB();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  } // run

  String sentance60 = "Neque porro quisquam est qui dolorem ipsum quia dolor";
  String paragraph255 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur rutrum, neque id vulputate pulvinar, urna diam aliquet massa, quis aliquet dolor dui a sapien. Sed malesuada metus sit amet cursus faucibus. Suspendisse faucibus arcu eu quam dignissim,";
  String page1000 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur rutrum, neque id vulputate pulvinar, urna diam aliquet massa, quis aliquet dolor dui a sapien. Sed malesuada metus sit amet cursus faucibus. Suspendisse faucibus arcu eu quam dignissim, eu posuere nulla elementum. Maecenas in sodales orci. Quisque lacinia placerat volutpat. Nullam rutrum est eros, ac euismod mauris volutpat sit amet. Pellentesque vitae lacus eu purus congue consectetur vel vel lectus. Suspendisse potenti. Integer blandit justo a malesuada consectetur. Nullam vel blandit dui. Ut volutpat pellentesque nulla, eu hendrerit nunc condimentum et. Aliquam a arcu nec odio consectetur laoreet. Curabitur eget pharetra quam, non vehicula dolor. Phasellus blandit congue lacus, eget porttitor ligula elementum nec.";
  String page3500 =
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur rutrum, neque id vulputate pulvinar, urna diam aliquet massa, quis aliquet dolor dui a sapien. Sed malesuada metus sit amet cursus faucibus. Suspendisse faucibus arcu eu quam dignissim, eu posuere nulla elementum. Maecenas in sodales orci. Quisque lacinia placerat volutpat. Nullam rutrum est eros, ac euismod mauris volutpat sit amet. Pellentesque vitae lacus eu purus congue consectetur vel vel lectus. Suspendisse potenti. Integer blandit justo a malesuada consectetur. Nullam vel blandit dui. Ut volutpat pellentesque nulla, eu hendrerit nunc condimentum et. Aliquam a arcu nec odio consectetur laoreet. Curabitur eget pharetra quam, non vehicula dolor. Phasellus blandit congue lacus, eget porttitor ligula elementum nec.\n"
          + "\n"
          + "Vestibulum tellus lorem, pulvinar at mauris eget, facilisis consectetur massa. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed in vulputate nibh. Mauris maximus justo quis risus blandit, vel pellentesque justo luctus. Nunc non ante nunc. Nulla imperdiet tincidunt leo, non pharetra quam scelerisque vitae. Vestibulum euismod, libero eu venenatis bibendum, arcu lacus vulputate massa, sit amet bibendum nunc sem sed mauris. Nulla viverra arcu lorem, ac scelerisque nisl sodales sed. Nunc libero ex, facilisis eget ex varius, consequat eleifend nunc. Etiam purus ipsum, ultrices sodales tempor in, porta vitae diam. Vivamus congue egestas facilisis. Cras a arcu ut dui tempor vehicula. Etiam varius interdum tincidunt. Etiam blandit fringilla blandit. Vestibulum faucibus tincidunt semper.\n"
          + "\n"
          + "Sed tempus id ipsum nec faucibus. Proin maximus orci at varius congue. Etiam imperdiet imperdiet lacus elementum luctus. Curabitur dictum molestie dui a consequat. Donec et eros gravida, condimentum risus ac, tempus quam. Sed dolor mauris, consectetur nec consequat aliquam, molestie at nibh. Aenean eleifend elementum blandit. Pellentesque nec mollis diam, eget facilisis sem. Phasellus varius ullamcorper felis a laoreet. Curabitur vel nulla semper tortor pellentesque gravida. Etiam at consequat ipsum, ac gravida dui. Donec consectetur risus eu purus fringilla, ut semper metus pharetra. Ut sed nibh laoreet, congue arcu nec, luctus eros.\n"
          + "\n"
          + "Proin rutrum non est quis finibus. Curabitur in ante vel nibh accumsan pharetra quis eu tellus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Mauris sagittis odio purus, ac lacinia nisl porttitor auctor. Nam quis fermentum justo, eget imperdiet massa. Vestibulum sit amet condimentum est. Nulla sollicitudin, arcu at elementum volutpat, lorem arcu venenatis lacus, sed scelerisque augue felis mollis ex. Fusce laoreet pulvinar erat et rhoncus. Donec mauris elit, semper at consectetur id, accumsan ut urna. Duis tempus eleifend erat, molestie vulputate nibh pulvinar pharetra. Integer vitae ultricies nibh, sed volutpat nibh. Etiam sit amet viverra justo.\n"
          + "\n"
          + "Cras ut accumsan ligula, non condimentum nisl. Pellentesque vitae vulputate neque. Cras gravida purus sit amet nisi porta feugiat. Nulla sed vestibulum metus, sed aliquam urna. Aenean eu justo ac velit dictum maximus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean nisl eros, tempus viverra risus eu, suscipit fringilla odio. Sed elementum diam vitae diam porttitor interdum. Duis eu lorem in quam bibendum luctus. Nunc et felis at elit fermentum ultricies.";

  private String generateTitle(String first) {
    String num = String.valueOf(Math.round(Math.random() * 10000));
    if (num == "0") {
      num = String.valueOf(Math.round(Math.random() * 10000));
    }
    return first + "." + num;
  }

  private void seedDB() {
    seedMessages(5);
  } // seedDB

  private void seedMessages(int num) {
    List<Message> messages = new ArrayList<>();
    for (int i=0; i<num; i++) {
      String to = generateTitle("You") + "@email.com";
      String from = generateTitle("Me") + "@email.com";
      messages.add(new Message(to,from,paragraph255));
//      messageRepository.save(new Message(to, from, paragraph255));
    }
    messageRepository.saveAll(messages);
  }
} // DemoData Class
