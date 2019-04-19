<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
  <body>
  <script src="<c:url value="/webjars/sockjs-client/0.3.4/sockjs.min.js" />"></script>
  <script src="<c:url value="/webjars/stomp-websocket/2.3.0/stomp.min.js" />"></script>
  <script src="<c:url value="/webjars/jquery/2.0.3/jquery.min.js" />"></script>

  <security:authorize access="isAuthenticated()">
      <security:authentication property="principal.username" var="loginId"/>
      Hello ${loginId}!  <a href="/logout">退出</a>
  </security:authorize>
  	<p>
  	Try opening this app in two separate browsers.
  	Then post messages in each to see them sent to the other browser.
  	Try mentioning each user (@fred and @chuck) to see the user-targeted "You just got mentioned" message appear in the appropriate user's browser.
  	</p>
  
  	<form id="bittleForm">
  		<textarea rows="4" cols="60" name="text"></textarea>
  		<input type="submit"/>
  	</form>
  
    <script>
    	$('#bittleForm').submit(function(e){
    		e.preventDefault();
    		var text = $('#bittleForm').find('textarea[name="text"]').val();
    		sendBittle(text);
    	});
    	var url = 'bittler';
      var sock = new SockJS("<c:url value='/bittler'/>");
      var stomp = Stomp.over(sock);

      stomp.connect('guest', 'guest', function(frame) {
        console.log('*****  Connected  *****');
        // stomp.subscribe("/topic/bittlefeed", handleBittle);
        stomp.subscribe("/topic/bittlefeed", handleNotification);
        stomp.subscribe("/user/queue/notifications", handleNotification);
      });
      
      function handleBittle(message) {
    	  console.log('Bittle:', message);
    	  $('#output').append("<b>Received bittle: " + JSON.parse(message.body).message + "</b><br/>");
      }
      
      function handleNotification(message) {
        console.log('Notification: ', message);
        $('#output').append("<b>Received: " + 
            JSON.parse(message.body).message + "</b><br/>")
      }
      
      function sendBittle(text) {
        console.log('Sending Bittle');
        stomp.send("/app/bittle", {},
            JSON.stringify({ 'message': text ,'longitude':0 ,'latitude':0}));
      }

      $('#stop').click(function() {sock.close()});
      </script>
    
    <div id="output"></div>
  </body>