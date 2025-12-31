/**
 * 
 */
document.addEventListener('DOMContentLoaded', function() {

	class ButtonRenderer {
		constructor(props) {
			const el = document.createElement('button');
			el.innerText = '주소록에 추가';
			el.className = 'btn btn-sm btn-info';
			el.addEventListener('click', () => {
				const rowData = props.grid.getRow(props.rowKey);
				console.log(rowData);

				$.ajax({
					url: '/adrs/insert',
					method: 'POST',
					data: {
						adrsName: rowData.memName,
						adrsTel: rowData.memHp,
						adrsAdd: rowData.memAdd1,
						adrsMail: rowData.memMail,
					},
					success: function(data) {
						alert(data);
					},
					error: function(xhr, status, error) {
						alert("로그인이 되어있는지 확인해주세요.");
					}
				});
			});
			this.el = el;
		}
		getElement() {
			return this.el;
		}
	}

	const memberGrid = new tui.Grid({
		el: document.getElementById('memberGrid'),
		data: [], // 초기 데이터는 빈 배열
		scrollX: false,
		scrollY: false,
		bodyHeight: 400,
		columns: [
			{ header: '이름', name: 'memName' },   // MemberDTO 필드명에 맞춤
			{ header: '아이디', name: 'memId' },
			{ header: '연락처', name: 'memHp' },
			{ header: '이메일', name: 'memMail' },
			{ header: '주소', name: 'memAdd1' },
			{
				header: '관리',
				name: 'manage',
				renderer: { type: ButtonRenderer } // 💡 위에서 만든 렌더러 연결
			}
		]
	});


	$.ajax({
		url: '/member/list',
		method: 'GET',
		dataType: 'json',
		success: function(data) {
			console.log("서버에서 받은 데이터:", data);
			memberGrid.resetData(data);
		},
		error: function(xhr, status, error) {
			console.error("데이터 로드 실패:", error);
			alert("로그인을 해주세요");
		}
	});
});