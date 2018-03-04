package org.jxau.lctoh.tool.config.statistical;

import org.springframework.stereotype.Component;

/**
 * 统计信息常量池
 * @author qdt_PC
 */
@Component("Statistical")
public class Statistical {
	/**日统计时间节点(小于14天按日统计)*/
	public final static long dayNode=1000*60*60*24*14;
	/**周统计时间节点(大于14天小于90天按周统计)*/
	public final static long weekNode=1000*60*60*24*90;
	/**月统计时间节点(大于90天小于365天按月统计)*/
	public final static long mouthNode=1000*60*60*24*365;
	/**季度统计时间节点(小于365天按月统计，没有给时间段还有全部统计按季度统计来计算)*/
	public final static long quarterNode=1000*60*60*24*365;
}
