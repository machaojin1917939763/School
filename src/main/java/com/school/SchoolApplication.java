package com.school;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;


@SpringBootApplication
@EnableRedisRepositories(value = "com.school.redis.mapper")//JPA使用redis，并且设置实体类路径
@EnableJpaRepositories(value = "com.school.jpa.mapper")//JPA使用mysql,并且设置实体类路径
@MapperScan(value = "com.school.thymeleaf.mapper")
public class SchoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolApplication.class, args);
        System.out.println("            _\n" +
                "           ( `''--.  .-.\n" +
                "            `'-.   `\\|  | _\n" +
                "                `\\      /` )\n" +
                "               _.'-  ._   (_\n" +
                "              ;-.-.     '.  `\\\n" +
                "             /  /  \\      '.(`\n" +
                "           __\\o_\\o_/        ,`)\n" +
                "      .-'``  `\"\"-.           \\\n" +
                "       `''--.___, `\\         }\n" +
                "             / / .-`     ;   }\n" +
                "            /_/.'`-....-'|   }             _   _\n" +
                "            `\"`          /   /           .' \\.' \\\n" +
                "                        /   /            |  /   /  _\n" +
                "                __..--/`   (__ _..----.._/     L.-' )\n" +
                "      .--...--''    /`        `         .--. _     `-.\n" +
                "     (_ __.       /`             -.    '    `\\`\"\"--.,_)\n" +
                "       (_'__.    {                 )          |\n" +
                "         '._ -  _{               .'         ,_/\n" +
                "           '--'` {             .'        ,_/\n" +
                "                  \\           (_,    ._.'\n" +
                "                   '.           /    |\n" +
                "                     '-.  _    |    /\n" +
                "                        `-||.--;\\__/\n" +
                "              jgs  __   __||_   ||_\n" +
                "                  (_.```  \"` `)(`\" `-.__ _\n" +
                "                 (_.-'   .-\"\"`  '-.   -.`_)\n" +
                "                   '.__.'          \\    )  )\n" +
                "                                    '--'`\"`");
    }

}
