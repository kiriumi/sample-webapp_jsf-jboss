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
## 基盤
* JavaSE 8
* JavaEE 8

## APサーバ
* WildFly 17

## DB
* PostgreSQL 11.7

## Webアプリケーション
* JSF 2.3
  * PrimeFaces 3.4
  * OmniFaces 1.1

## DBアクセス
* MyBatis 3.4
* JPA (+ EcliseLink)

## ログ
* SLF4J + Log4j2

## テスト
* JUnit 5.x
* Selenide

## ビルド
* Gradle 4.1

## CSS
* Bootstrap
  * BootsFaces
