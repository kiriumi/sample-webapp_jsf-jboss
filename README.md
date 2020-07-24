# 概要
Webアプリケーションのアーキテクチャや主要な機能を学ぶための、サンプル集

# 方針
* JavaEEを主体とする
* Springフレームワークは使用しない
* CDI管理Beanを主体とする（EJBは原則使わない）
* トランザクション制御と認証は、APサーバが行う
* 認可処理はWebアプリケーションで行う（∵アノテーションによる認可処理はEJBのみ対応しているため）
* 環境ごとの接続情報は、全て外部で管理する（Webアプリケーションから除外する）

# アーキテクチャ
* オニオン・アーキテクチャ

∵ドメイン駆動設計を主体とするため

# 使用するフレームワーク・ライブラリ
## 言語
* JavaSE 8.x
* JavaEE 8.x

## APサーバ
* WildFly 17.x

## DB
* PostgreSQL 11.x

## Webアプリケーション
* JSF 2.3
  * PrimeFaces 3.x
  * OmniFaces 1.x

## DBアクセス
* MyBatis 3.x
* JPA (+ EcliseLink)

## ログ
* SLF4J + Log4j2

## テスト
* JUnit 5.x
* Selenide
* Mockit
* DB Unit

## ビルド
* Gradle 4.1

## CSS
* Bootstrap 4

# JavaScrip
* jQuery

# 機能
## Webアプリ開発機能
* 入力チェック（フォーカス外した際に、即時単項目チェックも可能）
* メッセージ管理（ダイアログで複数メッセージを表示も可能）
* Ajax

## Web API
* RESTful
* SOAP

## DBアクセス
* MyBatis
* JPA

## セキュリティ対策
* 認証（Webアプリ・REST）
* 認可（WEBアプリ・REST）
* XSS
* SQLインジェクション
* CSRF

## その他
* ロギング
* 外部ディレクトリのクラスローディング
* プロパティ管理
* 二重送信防止
* トランザクショントークンチェック
* サブミット中のローディング表示（Ajax, Non Ajax対応）
* フレキシブルデザイン
* 郵便番号による住所検索
* 一時作成ファイル削除可能なファイルダウンロード
* カレンダーによる日付入力
