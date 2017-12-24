package learnJavaNetProgam.NIO;

import java.nio.CharBuffer;

/**
 * buffer的一些概念
 * 
 * 1.position 位置
 * 缓冲区中将读取或写入的下一个位置。这个位置从0开始计，最大值等于缓冲区的大小。可以用以下方法获取和设置：
 * public final int    position()
 * public final Buffer position(int newPosition)
 * 
 * 2.capacity 容量
 * 缓冲区可以保存的元素的最大数量。容量值在创建缓冲区的时候设置，之后不能改变。可以用以下方法获取：
 * public final int capacity()
 * 
 * 3.limit 限度
 * 缓冲区中可访问数据的末尾位置。只要不改变限度，就无法去读/写超过这个位置的数据，即使缓冲区的容量再大也没有用。限度可以用以下方法获取和设置：
 * public final int    limit()
 * public final Buffer limit(int newLimit)
 * 
 * 4.mark 标记
 * 缓冲区中的一个指定的索引。通过mark()可以标记设置为当前的位置，调用reset()可以将当前的位置返回到mark的位置：
 * public final Buffer mark()
 * public final Buffer reset()
 * 因为mark和reset是一个用来回到过去（类似存档和读档）的一个返回操作，所以如果将position设置的低于mark的位置，那么会自动丢弃这个mark
 * 说到这就不得不提一下，读取buffer块 和 inputstream等流 是有一些区别的,读取流中的数据会让流中的数据消失（被取走），而读取缓冲区的数据
 * 则不会以任何方式改变缓冲区内的数据。实际上只是向前或向后设置了position，从而可以从缓冲区中某个特定的位置开始读取数据。类似的，程序可以通过
 * 调整limit来控制要读取数据的末尾。总之，只有容量是限定的。
 * 
 * 5.clear()
 * clear方法将position设置为0，并且将limit设置为capacity大小，从而将缓冲区“清空”，这样就能完全重新填充缓冲区了。
 * public final buffer clear()
 * 需要注意的是，clear并没有删除缓冲区的老数据。这些数据任然存在，可以使用绝对的get方法 或者 重新改变limit和position来进行读取
 * 
 * 6.compact()
 * compact的概念有些复杂，他与上面的clear有些类似，clear通过重置position和limit来使buffer完全“清空”，而compact在清空数据时
 * 会“保留”未读取的数据。要知道读取buffer的本质 是读取 position与limit之间的数据，读取一个数据那么position的位置就+1，有点类似指针的意思。
 * 在执行compact操作时，position与limit之间如果有数据，那么这些未读的数据会被复制到buffer的开始，然后把段数据后+1的地方作为当前position
 * 也就是 position = limit - position。同时 会将limit的限定扩大至buffer的末尾 也就是  limit = capacity。
 * 
 * 7.rewind()
 * 将position设置为0，不改变limit。通过重置position来实现重新读取缓冲区
 * public final Buffer rewind()
 * 
 * 8.flip()
 * 将limit设置为当前的position，position设置为0。通过这样可以通过channel输出buffer中刚刚写入的数据。一般在执行channel.wirte(buffer)
 * 之前使用
 * public final Buffer flip()
 * 
 * 9.remain
 * 最后还有2个方法来返回buffer内的信息，但不改变这些信息。remaining()返回position与limit之间的元素数，告知你是否还有数据未读。如果这个数据
 * 大于0，那么hasRemaining()方法返回true，反之亦然。
 * public final int remaining()
 * public final boolean hasRemaining()
 * 
 * @author chenhaoyu
 *
 */
public class buffertest {

	public static void main(String[] args) {
		
	
		TestClear();
		System.out.println();
		TestCompact();
		
	
	}

	/*
	 * clear()与compact()方法
	 * 一旦读完Buffer中的数据，需要让Buffer准备好再次被写入。可以通过clear()或compact()方法来完成。
	 * 如果调用的是clear()方法，position将被设回0，limit被设置成 capacity的值。换句话说，Buffer
	 * 被清空了。Buffer中的数据并未清除，只是这些标记告诉我们可以从哪里开始往Buffer里写数据。
	 * 如果Buffer中有一些未读的数据，调用clear()方法，数据将“被遗忘”，意味着不再有任何标记会告诉你哪些数据被读过，哪些还没有。
	 * 如果Buffer中仍有未读的数据，且后续还需要这些数据，但是此时想要先先写些数据，那么使用compact()方法。
	 * compact()方法将所有未读的数据拷贝到Buffer起始处。然后将position设到最后一个未读元素正后面。limit属性依然像clear(
	 * )方法一样，设置成capacity。现在Buffer准备好写数据了，但是不会覆盖未读的数据。
	 */

	/**
	 * 输出 AC
	 */
	public static void TestClear() {
		CharBuffer buf = CharBuffer.allocate(1024);
		buf.put("AB");
		buf.flip();
		System.out.println((char) buf.get());
		buf.clear();
		buf.put("C");
		buf.flip();
		System.out.println((char) buf.get());
	}

	/**
	 * 输出 AB
	 */
	public static void TestCompact() {
		CharBuffer buf = CharBuffer.allocate(1024);
		buf.put("AB");
		buf.flip();
		System.out.println((char) buf.get());
		buf.compact();
		buf.put("C");
		buf.flip();
		System.out.println((char) buf.get());
	}

}
