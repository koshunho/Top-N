# Top-N
Redis Zset

## 需求
- top N
- 个人排名，和周围人的位次

注意，在Zset中， 排行默认是0为开头，因此实际的排名需要+1

## 测试

用RestTemplate来请求API

RestTemplate来请求API就是 Spring 封装的处理同步 HTTP 请求的类

详解[RestTemplate](https://juejin.im/post/5cd680eff265da037b612e28#heading-3)

此处Zset的key为""global_rank""
```java
public class Top_N {
    private Random random;
    private RestTemplate restTemplate;

    public void init() {
        random = new Random();
        restTemplate = new RestTemplate();
    }

    private int genUserId() {
        return random.nextInt(1024);
    }

    private double genScore() {
        return random.nextDouble() * 100;
    }

    @Test
    public void testInitRank() {
        init();
        try {
            for (int i = 0; i < 50; i++) {
                restTemplate.getForObject("http://localhost:8080/update?userId=" + genUserId() + "&score=" + genScore(),
                        String.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
##### Top 10
![Top 10](http://qcorkht4q.bkt.clouddn.com/blog1595231187449.png)

##### 根据userId查询排名
![根据userId查询排名](http://qcorkht4q.bkt.clouddn.com/blog1595231275701.png)

##### 根据userId查询上下n个名次
![根据userId查询上下n个名次](http://qcorkht4q.bkt.clouddn.com/blog1595231333863.png)

idea by @一灰灰
