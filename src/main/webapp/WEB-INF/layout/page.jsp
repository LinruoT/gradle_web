<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ page session="false" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<html>
  <head>
    <title>Bitter</title>
    <link rel="stylesheet" 
          type="text/css" 
          href="<s:url value="/resources/style.css" />" >
  <link href="https://fonts.googleapis.com/css?family=Long+Cang|Noto+Sans+SC&display=swap" rel="stylesheet">
  <script src="<s:url value="/resources/main.js" />" async ></script>
    <!-- Global site tag (gtag.js) - Google Analytics -->
    <script async src="https://www.googletagmanager.com/gtag/js?id=UA-147949008-1"></script>
    <script>
      window.dataLayer = window.dataLayer || [];
      function gtag(){dataLayer.push(arguments);}
      gtag('js', new Date());

      gtag('config', 'UA-147949008-1');
    </script>

  </head>
  <body>
    <header id="header">
      <t:insertAttribute name="header" />
    </header>
    <main id="content">
      <t:insertAttribute name="body" />
    </main>
    <footer id="footer">
      <t:insertAttribute name="footer" />
    </footer>
  </body>
</html>
