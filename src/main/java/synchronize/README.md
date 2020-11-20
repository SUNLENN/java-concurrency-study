#### synchronized 关键字

##### 1. 内置锁/内置锁/监视器锁

- 每一个对象都隐式的包含一个内置锁
- 内置锁是一个排它锁，线程进入<b>synchronized</b>的代码块的时候会去争用这把锁，成功获取锁的线程进入代码块，未成功获取锁的线程则进入阻塞状态
- 一个线程退出代码块/抛出异常/调用wait方法时会释放锁
- 重量级：争用失败后会进入阻塞状态，引起上下文切换从而增大开销


##### 2. Java内存模型

- Java内存模型将内存分为两种类型，即主内存（共享内存）和私有内存（或称工作内存），从目前实际的架构来说，CPU的一级缓存（归属于CPU上的某个核心）可以被视为一个线程的的私有内存，而内存（有时候还包含CPU的二级缓存）则可以被视为主内存。
- 由于这样的内存模型带来了一个常见的问题
    - 可见性问题：当某个CPU核心的1级缓存缓存了主内存上某个变量的副本，则当主内存上该变量的值发生改变时，运行在该CPU核心上的线程还从1级缓存获取过时的变量值，即主内存上变量值的更新对该线程不可见
- synchronized能够解决可见性问题，当一个线程即将进入同步代码块时，会将代码块内部中的变量值从1级缓存（工作内存）中删除，从而保证可以获取到主内存上最新的变量值，当该线程离开同步代码块时，会将这些变量值刷新回主内存，从而保证变量值的更新对其他线程可见。
   

