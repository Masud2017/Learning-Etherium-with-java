package Web3;

import java.util.Iterator;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;
import java.io.File; // Import the File class
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Hello world!
 *
 */
public class App 
{
    private static EthGetBalance ethBalance = null;
    private static EthGetTransactionCount nonce = null;
    private static String private_key = "41889028ccbe379a9135558a13d29136a420cbb166e36adafb8cedf018059886";// Private key for send eth to second accoutn;
    private static String account_1 = "0xb28261a51ac8D948a4ecBDb5Dfc183e02f7C0A38";
    private static String account_2 = "0xDa017113e2382855aC8CA0F3BcC2D70b88185640";
    private static String jsonData;
    private static JSONParser parser = new JSONParser();


    //private static ObjectMapper mapper = new ObjectMapper();
    //private static TxModel txModel = null;
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Web3j web3 = Web3j.build(new HttpService("http://localhost:7545"));
        try {
            Web3ClientVersion clientVersion = web3.web3ClientVersion().send();
            EthBlockNumber blocknum = web3.ethBlockNumber().send();
            EthGasPrice gasPrice = web3.ethGasPrice().send();
            ethBalance  = web3.ethGetBalance("0xb28261a51ac8D948a4ecBDb5Dfc183e02f7C0A38", DefaultBlockParameterName.LATEST).send();

            nonce = web3.ethGetTransactionCount("0xb28261a51ac8D948a4ecBDb5Dfc183e02f7C0A38", DefaultBlockParameterName.LATEST).send();

            System.out.println("Value of nonce is : "+nonce.getTransactionCount());

            System.out.println("Balance from first account : "+Convert.fromWei(ethBalance.getBalance().toString(),Unit.ETHER));

            System.out.println("Client version: " + clientVersion.getWeb3ClientVersion());
            System.out.println("Block number: " + blocknum.getBlockNumber());
            System.out.println("Gas price: " + gasPrice.getGasPrice());

           // txModel = new TxModel(nonce,account_2,(int)Convert.toWei(1, Unit.ETHER),2000000,(int)Convert.toWei(50, Unit.GWEI));
           try {
               File fp = new File("src/main/java/Web3/abi.json");
               InputStream streamInput = new FileInputStream(fp);
               int i = 0;
               while((i = streamInput.read())!=-1) {
                
                jsonData+= (char)streamInput.read();
                
               }
               System.out.println(jsonData);
           } catch(FileNotFoundException e) {
               System.out.print("An error has occured");
               e.printStackTrace();
           }

           try {
               Object obj = parser.parse(new FileReader("src/main/java/Web3/abi.json"));
               JSONObject jsonObject = (JSONObject)obj;
               Iterator<String> keys = jsonObject.keys();
               while (keys.hasNext()) {
                   if(jsonObject.get(keys) instanceof JSONObject) {
//
                   }
               }
               

           } catch (JsonParseException e) {
            e.printStackTrace();
        }

        } catch(Exception ex) {
            throw new RuntimeException("Error while sending req",ex);
        }
       // Controller con = Controller.getControllerInstacne();
       /// Controller con2 = Controller.getControllerInstacne();
       // con.changeCounter();
       // con2.changeCounter();
    }
}
