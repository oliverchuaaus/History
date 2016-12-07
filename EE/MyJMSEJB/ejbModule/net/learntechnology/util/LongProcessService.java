package net.learntechnology.util;

import net.learntechnology.dto.ProcessDTO;

public class LongProcessService {
    private LongProcessService() {}

    public static ProcessResult doLongProcess(ProcessDTO processDTO ) {
        int result = (int)Math.round(Math.random());
        //sleep for a little bit
        try {
			System.out.println("Started Long Process. (LongProcessService name: "+processDTO.getName()+") ....");
            Thread.sleep(30000);
            System.out.println("Completed Long Process! (Leaving LongProcessService name: "+processDTO.getName()+")");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        if (result == 0) {
            return ProcessResult.FAIL;
        } else {
            return ProcessResult.PASS;
        }
    }
}
