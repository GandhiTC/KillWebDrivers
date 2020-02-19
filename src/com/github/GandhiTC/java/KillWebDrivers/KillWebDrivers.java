package com.github.GandhiTC.java.KillWebDrivers;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 *
 * This class can be called between tests
 * to forcibly kill off all lingering instances of
 * Chrome, Firefox, IE, and Edge WebDrivers.
 * <Br><Br>
 * Jan. 4, 2020 - Current version for Windows only.
 *
 * @param      args		<Br>0 = Disable print to console.
 * 						<Br>1 = Print to console only the processes that are killed.
 * 						<Br>2 = Print to console the whole list of running processes and denote those that are killed.
 *
 */
public class KillWebDrivers
{
	public static void main(String[] args)
	{
		if(args.length == 0)
		{
			args	= new String[1];
			args[0]	= "0";
		}

		int printOutType;

		try
		{
			printOutType = Integer.parseInt(args[0]);
		}
		catch(NumberFormatException e)
		{
			printOutType = 0;
		}

		printOutType = printOutType > 2 ? 2 : printOutType < 0 ? 0 : printOutType;

		//	for linux, start with
		//	https://stackoverflow.com/a/54950
		//	https://stackoverflow.com/a/48705593
		//	https://itsfoss.com/online-linux-terminals/

		if(System.getProperty("os.name").toLowerCase().contains("windows".toLowerCase()))
		{
			try
			{
				String			line;
				Runtime			runTime			= Runtime.getRuntime();
				Process			process			= runTime.exec("tasklist.exe");
				BufferedReader	bufferedReader	= new BufferedReader(new InputStreamReader(process.getInputStream()));

				if(printOutType == 2)
				{
					System.out.println("Image Name                     PID Session Name        Session#    Mem Usage");
					System.out.println("========================= ======== ================ =========== ============");
				}

				while((line = bufferedReader.readLine()) != null)
				{
					if(line.toLowerCase().contains(".exe".toLowerCase()) && line.toLowerCase().contains("Console".toLowerCase()))
					{
						String imageName = line.substring(0, line.indexOf(".exe") + 4).trim();

						if(imageName.equalsIgnoreCase("chromedriver.exe")		||
						   imageName.equalsIgnoreCase("geckodriver.exe")		||
						   imageName.equalsIgnoreCase("IEDriverServer.exe")		||
						   imageName.equalsIgnoreCase("MicrosoftWebDriver.exe"))
						{
							String	pID				= line.substring(line.indexOf(".exe") + 4, line.indexOf("Console")).trim();
//							String	tempString		= line.substring(line.indexOf("Console") + 7, line.length() - 1).trim();
//							String	sessionNumber	= tempString.substring(0, tempString.indexOf(" ")).trim();
//							String	memUsage		= line.substring(line.indexOf(sessionNumber) + sessionNumber.length(), line.length() - 1).trim();

							if(printOutType == 1)
								System.out.println("**Killing Process/PID** --> [" + imageName + " / " + pID + "]");

							else if(printOutType == 2)
								System.out.println(line + " <-- **Killing This Process**");

							runTime.exec("taskkill /F /IM " + imageName + " /T");
						}
						else
						{
							if(printOutType == 2)
								System.out.println(line);
						}
					}
				}

				bufferedReader.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}