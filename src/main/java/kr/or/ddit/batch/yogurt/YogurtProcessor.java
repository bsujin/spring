package kr.or.ddit.batch.yogurt;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import kr.or.ddit.yogurt.model.CycleVo;
import kr.or.ddit.yogurt.model.DailyVo;

public class YogurtProcessor implements ItemProcessor<CycleVo, List<DailyVo>>{
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
//		JobParameters를 사용 
	//batch를 실행할 때 파라미터를 실행 - 여러개의파라미터를 넣을 수 있다 예)로그인 할때 사용자id, 비밀번호 
	@Value("#{jobParameters[dt]}")	//파라미터중에 dt라는 파라미터를 주입하겠다는 의미 
	private Date dt;
	
	// 배치 당시의 년월일구하기 --> rangerBatch
	// dt : 202102, item : cid-1, pid-100, day-2, cnt-1
	// ==> cid-1, pid-100, dt-202010201, cnt-1
	// ==> cid-1, pid-100, dt-202010208, cnt-1
	// ==> cid-1, pid-100, dt-202010215, cnt-1
	// ==> cid-1, pid-100, dt-202010222, cnt-1
	// 해당 년월에 해당하는 일월적 데이터 만들기 ==> 해당년월의 1일부터 마지막 날짜까지 루프돌리기
	
	// 날짜 : 1일~28일 loop
	// if(요일 == item.요일과 같은지 체크 )
	// 같으면 해당 일자로 일실적 데이터를 생성 
	// 28번 루프를 돌 때 if조건에 걸리는것은 4번 
	// 마지막 날짜가 언제인지 알기

	
	@Override
	public List<DailyVo> process(CycleVo item) throws Exception {

		// 해당 년월의 마지막 날짜(date)
		// 해당 년월의 첫번째 날짜 -1일(date)

		// 현재 날짜 시간 
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);	//데이트에 담긴 값을 캘린더에 설정해주기
		calendar.set(calendar.DAY_OF_MONTH,calendar.getActualMaximum(calendar.DAY_OF_MONTH));
		// 20210228 02:00:00
		Date endDt = calendar.getTime();
		
		calendar.set(calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);	//시간 값 고정 
		// 20210201 00:00:00
//		Date startDt = calendar.getTime();
		
		//List에 담아주기
		List<DailyVo> dailyVoList = new ArrayList<DailyVo>();
		
		// endDt가 클 동안 == 해당 년월이 마지막 날짜를 넘어 설 동안 루프를 돌기 
		while(endDt.compareTo(calendar.getTime()) > 0) {
			// 20210201 ==> 주간요일에 대한 정보 (cycle에서는 주간요일정보만 있음)
			if(item.getDay()==calendar.get(calendar.DAY_OF_WEEK)) {
					//cid, pid, dt(yyyyMMdd), cnt 
				dailyVoList.add(
						new DailyVo(item.getCid(), item.getPid(), sdf.format(calendar.getTime()), item.getCnt()));
			}
			calendar.set(calendar.DAY_OF_MONTH, calendar.get(calendar.DAY_OF_MONTH)+1);
		}
		
		return dailyVoList;
	}
	
	

}
