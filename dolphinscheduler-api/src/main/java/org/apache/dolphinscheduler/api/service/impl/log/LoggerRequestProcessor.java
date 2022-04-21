
package org.apache.dolphinscheduler.api.service.impl.log;

import io.netty.channel.Channel;
import org.apache.dolphinscheduler.common.utils.JSONUtils;
import org.apache.dolphinscheduler.common.utils.LoggerUtils;
import org.apache.dolphinscheduler.remote.command.Command;
import org.apache.dolphinscheduler.remote.command.CommandType;
import org.apache.dolphinscheduler.remote.command.log.*;
import org.apache.dolphinscheduler.remote.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * logger request process logic
 */
@Service
public class LoggerRequestProcessor   {

    private final Logger logger = LoggerFactory.getLogger(LoggerRequestProcessor.class);

//    private final ExecutorService executor;
//
//    public LoggerRequestProcessor() {
//        this.executor = Executors.newFixedThreadPool(Constants.CPUS * 2 + 1);
//    }

    public String process(final CommandType commandType,String path,int skip_line_num,int limit) {
        logger.info("received command : {}", commandType);

        /**
         * reuqest task log command type
         */
        switch (commandType) {
            case GET_LOG_BYTES_REQUEST:
//                GetLogBytesRequestCommand getLogRequest = JSONUtils.parseObject(
//                        command.getBody(), GetLogBytesRequestCommand.class);
//                byte[] bytes = getFileContentBytes(getLogRequest.getPath());
                break;
            case VIEW_WHOLE_LOG_REQUEST:
//                ViewLogRequestCommand viewLogRequest = JSONUtils.parseObject(
//                        command.getBody(), ViewLogRequestCommand.class);
//                String msg = LoggerUtils.readWholeFileContent(viewLogRequest.getPath());
                break;
            case ROLL_VIEW_LOG_REQUEST:
                List<String> lines = readPartFileContent(path, skip_line_num,limit);
                StringBuilder builder = new StringBuilder();
                for (String line : lines) {
                    builder.append(line + "\r\n");
                }
                return builder.toString();
            case REMOVE_TAK_LOG_REQUEST:
//                RemoveTaskLogRequestCommand removeTaskLogRequest = JSONUtils.parseObject(
//                        command.getBody(), RemoveTaskLogRequestCommand.class);
//
//                String taskLogPath = removeTaskLogRequest.getPath();
//
//                File taskLogFile = new File(taskLogPath);
//                Boolean status = true;
//                try {
//                    if (taskLogFile.exists()) {
//                        status = taskLogFile.delete();
//                    }
//                } catch (Exception e) {
//                    status = false;
//                }
                break;
            default:
                throw new IllegalArgumentException("unknown commandType");
        }
        return "--EMPTY--";
    }

//    public ExecutorService getExecutor() {
//        return this.executor;
//    }

    /**
     * get files content bytes，for down load file
     *
     * @param filePath file path
     * @return byte array of file
     * @throws Exception exception
     */
    private byte[] getFileContentBytes(String filePath) {
        try (InputStream in = new FileInputStream(filePath);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            logger.error("get file bytes error", e);
        }
        return new byte[0];
    }

    /**
     * read part file content，can skip any line and read some lines
     *
     * @param filePath file path
     * @param skipLine skip line
     * @param limit read lines limit
     * @return part file content
     */
    private List<String> readPartFileContent(String filePath,
                                             int skipLine,
                                             int limit) {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
                return stream.skip(skipLine).limit(limit).collect(Collectors.toList());
            } catch (IOException e) {
                logger.error("read file error", e);
            }
        } else {
            logger.info("file path: {} not exists", filePath);
        }
        return Collections.emptyList();
    }

}
